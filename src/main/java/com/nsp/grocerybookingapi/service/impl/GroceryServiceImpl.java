package com.nsp.grocerybookingapi.service.impl;

import com.nsp.grocerybookingapi.entity.Grocery;
import com.nsp.grocerybookingapi.exception.ResourceNotFoundException;
import com.nsp.grocerybookingapi.repository.GroceryRepository;
import com.nsp.grocerybookingapi.service.GroceryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryServiceImpl implements GroceryService {

    private final GroceryRepository groceryRepository;

    public GroceryServiceImpl(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }

    @Override
    public Grocery addGrocery(Grocery grocery) {
        return groceryRepository.save(grocery);
    }

    @Override
    public List<Grocery> getAllGroceries() {
        return groceryRepository.findAll();
    }

    @Override
    public Grocery getGroceryById(Long id) {
        return groceryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grocery not found with id: " + id));
    }

    @Override
    public Grocery updateGrocery(Long id, Grocery updatedGrocery) {
        Grocery existingGrocery = getGroceryById(id);
        existingGrocery.setName(updatedGrocery.getName());
        existingGrocery.setPrice(updatedGrocery.getPrice());
        existingGrocery.setInventory(updatedGrocery.getInventory());
        return groceryRepository.save(existingGrocery);
    }

    @Override
    public void deleteGrocery(Long id) {
        if (!groceryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Grocery not found with id: " + id);
        }
        groceryRepository.deleteById(id);
    }
}