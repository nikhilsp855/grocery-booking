package com.nsp.grocerybookingapi.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class OrderItem {

    private Long groceryId;
    private Integer quantity;

    public OrderItem(){

    }
    public OrderItem(long groceryId, int quantity) {
        this.groceryId=groceryId;
        this.quantity=quantity;
    }
}

