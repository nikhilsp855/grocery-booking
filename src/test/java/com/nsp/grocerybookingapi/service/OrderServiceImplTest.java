package com.nsp.grocerybookingapi.service;

import com.nsp.grocerybookingapi.entity.Grocery;
import com.nsp.grocerybookingapi.entity.Order;
import com.nsp.grocerybookingapi.entity.OrderItem;
import com.nsp.grocerybookingapi.repository.GroceryRepository;
import com.nsp.grocerybookingapi.repository.OrderRepository;
import com.nsp.grocerybookingapi.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceImplTest {

    // Mock the dependencies
    @Mock
    private GroceryRepository groceryRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void testPlaceOrder_Success() {
        Grocery groceryItem = new Grocery();
        groceryItem.setId(1L);
        groceryItem.setName("Apple");
        groceryItem.setPrice(1.5);
        groceryItem.setInventory(10);

        when(groceryRepository.findById(1L)).thenReturn(Optional.of(groceryItem));

        Order orderRequest = new Order();
        orderRequest.setUserId("user123");
        orderRequest.setOrderItems(Collections.singletonList(new OrderItem(1L, 2)));  // Ordering 2 Apples (Item ID matches)

        String response = orderService.placeOrder(orderRequest);

        assertTrue(response.contains("Order placed successfully"));

        verify(groceryRepository).save(groceryItem); // Ensure that the inventory was updated
    }

    @Test
     void testPlaceOrder_ItemUnavailable() {

        Grocery groceryItem1= new Grocery();
        groceryItem1.setId(1L);
        groceryItem1.setName("Apple");
        groceryItem1.setPrice(1.5);
        groceryItem1.setInventory(10);

        Grocery groceryItem2 = new Grocery();
        groceryItem2.setId(2L);
        groceryItem2.setName("Banana");
        groceryItem2.setPrice(5.0);
        groceryItem2.setInventory(1); // Only 1 item in inventory

        when(groceryRepository.findById(1L)).thenReturn(Optional.of(groceryItem1));
        when(groceryRepository.findById(2L)).thenReturn(Optional.of(groceryItem2));


        Order orderRequest = new Order();
        orderRequest.setUserId("user123");
        orderRequest.setOrderItems(List.of(new OrderItem(1L, 2), new OrderItem(2L, 2)));  // Ordering 2 bananas, but only 1 is available

        String response = orderService.placeOrder(orderRequest);

        System.out.println(response);

        assertTrue(response.contains("Unavailable items: Banana (Requested Quantity: 2)"));
    }

    @Test
     void testPlaceOrder_ItemNotFound() {
        when(groceryRepository.findById(99L)).thenReturn(Optional.empty()); // Item with ID 99 doesn't exist

        Order orderRequest = new Order();
        orderRequest.setUserId("user123");
        orderRequest.setOrderItems(Collections.singletonList(new OrderItem(99L, 2)));  // Non-existent item with ID 99

        String response = orderService.placeOrder(orderRequest);

        assertTrue(response.contains("No items in the order are available."));
    }
}