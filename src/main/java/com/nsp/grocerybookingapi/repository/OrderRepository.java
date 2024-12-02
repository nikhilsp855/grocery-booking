package com.nsp.grocerybookingapi.repository;

import com.nsp.grocerybookingapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
