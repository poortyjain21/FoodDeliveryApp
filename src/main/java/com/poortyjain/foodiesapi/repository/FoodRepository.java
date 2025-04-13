package com.poortyjain.foodiesapi.repository;

import com.poortyjain.foodiesapi.entity.FoodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<FoodEntity, String> {
}
