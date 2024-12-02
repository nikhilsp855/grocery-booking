package com.nsp.grocerybookingapi.controller;

import com.nsp.grocerybookingapi.entity.Grocery;
import com.nsp.grocerybookingapi.entity.Order;
import com.nsp.grocerybookingapi.service.GroceryService;
import com.nsp.grocerybookingapi.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final GroceryService groceryService;
    private final OrderService orderService;

    public UserController(GroceryService groceryService, OrderService orderService) {
        this.groceryService = groceryService;
        this.orderService = orderService;
    }

    @GetMapping("/groceries")
    public ResponseEntity<List<Grocery>> getAvailableGroceries() {
        List<Grocery> groceries = groceryService.getAllGroceries();
        return ResponseEntity.ok(groceries);
    }

    @PostMapping("/orders")
    public ResponseEntity<String> bookGroceries(@RequestBody Order order) {
        String responseMessage = orderService.placeOrder(order);

        // Check if the response message contains "No items" or if it was a failure
        if (responseMessage.contains("No items") || responseMessage.contains("Item ID")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
