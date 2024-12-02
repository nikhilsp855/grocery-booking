package com.nsp.grocerybookingapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @ElementCollection
    private List<OrderItem> orderItems;

    // Getters and setters
}
