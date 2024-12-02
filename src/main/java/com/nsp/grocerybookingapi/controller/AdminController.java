package com.nsp.grocerybookingapi.controller;

import com.nsp.grocerybookingapi.entity.Grocery;
import com.nsp.grocerybookingapi.service.GroceryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin/groceries")
public class AdminController {

    private final GroceryService groceryService;

    public AdminController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @PostMapping
    public ResponseEntity<Grocery> addGrocery(@Valid @RequestBody Grocery grocery) {
        return ResponseEntity.ok(groceryService.addGrocery(grocery));
    }

    @GetMapping
    public ResponseEntity<List<Grocery>> getAllGroceries() {
        return ResponseEntity.ok(groceryService.getAllGroceries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grocery> getGroceryById(@PathVariable Long id) {
        return ResponseEntity.ok(groceryService.getGroceryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grocery> updateGrocery(
            @PathVariable Long id,
            @Valid @RequestBody Grocery updatedGrocery) {
        return ResponseEntity.ok(groceryService.updateGrocery(id, updatedGrocery));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrocery(@PathVariable Long id) {
        groceryService.deleteGrocery(id);
        return ResponseEntity.noContent().build();
    }
}
