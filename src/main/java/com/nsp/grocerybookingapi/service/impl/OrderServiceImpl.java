package com.nsp.grocerybookingapi.service.impl;

import com.nsp.grocerybookingapi.entity.Grocery;
import com.nsp.grocerybookingapi.entity.Order;
import com.nsp.grocerybookingapi.entity.OrderItem;
import com.nsp.grocerybookingapi.repository.GroceryRepository;
import com.nsp.grocerybookingapi.repository.OrderRepository;
import com.nsp.grocerybookingapi.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final GroceryRepository groceryRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(GroceryRepository groceryRepository, OrderRepository orderRepository) {
        this.groceryRepository = groceryRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public String placeOrder(Order orderRequest) {
        // Validate and update inventory
        List<OrderItem> processedItems = new ArrayList<>();
        List<String> unavailableItems = new ArrayList<>();

        // Check inventory for each item in the order
        for (OrderItem item : orderRequest.getOrderItems()) {
            Optional<Grocery> grocery = groceryRepository.findById(item.getGroceryId());

            if (grocery.isPresent() && grocery.get().getInventory() >= item.getQuantity()) {
                // If the item is in stock, process the order
                Grocery groceryItem = grocery.get();
                groceryItem.setInventory(groceryItem.getInventory() - item.getQuantity());
                groceryRepository.save(groceryItem);
                processedItems.add(item);
            } else {
                // If the item is unavailable, add it to the list of unavailable items with item name
                Grocery groceryItem = grocery.orElse(null);
                if (groceryItem != null) {
                    unavailableItems.add(groceryItem.getName() + " (Requested Quantity: " + item.getQuantity() + ")");
                } else {
                    unavailableItems.add("Item ID " + item.getGroceryId() + " not found");
                }
            }
        }

        // If no items were processed, return an error
        if (processedItems.isEmpty()) {
            return "No items in the order are available.";
        }

        // Create the order with the processed items
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setOrderItems(processedItems);
        orderRepository.save(order);

        // Construct response message
        String responseMessage = "Order placed successfully.";
        if (!unavailableItems.isEmpty()) {
            responseMessage += "\nUnavailable items: " + String.join(", ", unavailableItems);
        }

        return responseMessage;
    }

}