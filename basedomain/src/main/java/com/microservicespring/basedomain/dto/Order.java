package com.microservicespring.basedomain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long orderId;
    private String name;
    private int qty;
    private double price;
}
