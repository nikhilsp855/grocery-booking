package com.nsp.grocerybookingapi.repository;

import com.nsp.grocerybookingapi.entity.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryRepository extends JpaRepository<Grocery, Long> {
}
