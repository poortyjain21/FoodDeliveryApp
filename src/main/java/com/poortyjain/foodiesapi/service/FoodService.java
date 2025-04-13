package com.poortyjain.foodiesapi.service;

import com.poortyjain.foodiesapi.entity.FoodEntity;
import com.poortyjain.foodiesapi.io.FoodRequest;
import com.poortyjain.foodiesapi.io.FoodResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FoodService {

    public FoodResponse addFood(FoodRequest foodRequest);

    public List<FoodResponse> readFoods();

    public FoodResponse readFood(String id);

    public void deleteFood(String id);
}
