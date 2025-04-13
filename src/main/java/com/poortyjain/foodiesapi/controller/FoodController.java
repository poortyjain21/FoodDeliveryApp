package com.poortyjain.foodiesapi.controller;

import com.poortyjain.foodiesapi.io.FoodRequest;
import com.poortyjain.foodiesapi.io.FoodResponse;
import com.poortyjain.foodiesapi.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class FoodController {

    @Autowired
    FoodService foodService;

    @PostMapping("/api/foods")
    public ResponseEntity<FoodResponse> addFood(@RequestParam("name") String name,
                                                @RequestParam("description") String description,
                                                @RequestParam("category") String category,
                                                @RequestParam("price") double price,
                                                @RequestParam("image") MultipartFile imageFile
    )
    {
        FoodResponse foodResponse=null;
        try {
            String base64img = Base64.getEncoder().encodeToString(imageFile.getBytes());
            FoodRequest foodRequest = new FoodRequest(name, description,price,category, base64img);
            foodResponse = foodService.addFood(foodRequest);

        }
        catch(IOException e)
        {
            System.out.println("Error inside FoodController");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, ");
        }
        finally {

            return ResponseEntity.ok(foodResponse);

        }
    }

    @GetMapping("/api/readfoods")
    public ResponseEntity<List<FoodResponse>> readFoods()
    {

        return ResponseEntity.ok(foodService.readFoods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponse> readFood(@PathVariable String id)
    {
        return ResponseEntity.ok(foodService.readFood(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFood(@PathVariable String id)
    {
        foodService.deleteFood(id);
        return ResponseEntity.ok("Food Item deleted!");
    }
}
