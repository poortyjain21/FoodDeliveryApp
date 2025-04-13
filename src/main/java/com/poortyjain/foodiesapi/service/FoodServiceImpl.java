package com.poortyjain.foodiesapi.service;

import com.poortyjain.foodiesapi.entity.FoodEntity;
import com.poortyjain.foodiesapi.io.FoodRequest;
import com.poortyjain.foodiesapi.io.FoodResponse;
import com.poortyjain.foodiesapi.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodResponse addFood(FoodRequest foodRequest) {

        FoodEntity newFoodEntity = convertToEntity(foodRequest);
        newFoodEntity = foodRepository.save(newFoodEntity);

        return convertToResponse(newFoodEntity);
    }

    // In this method, fetched all food details from database and converted the List<FoodEntity> to List<FoodResponse> using Stream API.
    @Override
    public List<FoodResponse> readFoods() {

        List<FoodEntity> databseEntries = foodRepository.findAll();
        return databseEntries.stream().map(obj->convertToResponse(obj)).collect(Collectors.toList());

    }

    // Find the food details with id
    @Override
    public FoodResponse readFood(String id) {

        FoodEntity foodEntity = foodRepository.findById(id).orElseThrow(()->new RuntimeException("Food not found with id "+ id));
        return convertToResponse(foodEntity);
    }

    @Override
    public void deleteFood(String id) {
        FoodResponse getFoodItem = readFood(id);
        System.out.println("Details of Deleted Food item : "+getFoodItem);
        foodRepository.deleteById(id);
    }

    public FoodResponse convertToResponse(FoodEntity foodEntity)
    {
        return FoodResponse.builder()
                .name(foodEntity.getName())
                .price(foodEntity.getPrice())
                .category(foodEntity.getCategory())
                .description(foodEntity.getDescription())
                .id(foodEntity.getId())
                .image(foodEntity.getImage())
                .build();
    }

    public FoodEntity convertToEntity(FoodRequest foodRequest)
    {
        return FoodEntity.builder()
                .name(foodRequest.getName())
                .price(foodRequest.getPrice())
                .description(foodRequest.getDescription())
                .category(foodRequest.getCategory())
                .image(foodRequest.getImage())
                .build();

    }
}
