package com.poortyjain.foodiesapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "foods")

public class FoodResponse {

        @Id
        private String id;
        private String name;
        private String description;
        private double price;
        private String category;
        private String image;
    }

