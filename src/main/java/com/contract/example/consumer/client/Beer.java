package com.contract.example.consumer.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beer {
    private String brand;
    private BeerType type;
    private BigDecimal percentage;
}
