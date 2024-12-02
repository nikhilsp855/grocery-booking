package com.nsp.grocerybookingapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Grocery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.1", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Inventory cannot be null")
    @Min(value = 0, message = "Inventory must be non-negative")
    private Integer inventory;

    // Getters and Setters
}
