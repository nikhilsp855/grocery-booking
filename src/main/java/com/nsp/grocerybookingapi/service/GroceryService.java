package com.nsp.grocerybookingapi.service;

import com.nsp.grocerybookingapi.entity.Grocery;

import java.util.List;

public interface GroceryService {
    Grocery addGrocery(Grocery grocery);

    List<Grocery> getAllGroceries();

    Grocery getGroceryById(Long id);

    Grocery updateGrocery(Long id, Grocery updatedGrocery);

    void deleteGrocery(Long id);
}
