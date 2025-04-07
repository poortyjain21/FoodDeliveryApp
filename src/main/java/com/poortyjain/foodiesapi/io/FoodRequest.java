package com.poortyjain.foodiesapi.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FoodRequest {

    private String name;
    private String description;
    private double price;
    private String category;
    private String image;

}
