package com.derrick.cuisineconnect.controllers;

import com.derrick.cuisineconnect.dto.FoodRequestDTO;
import com.derrick.cuisineconnect.dto.FoodResponseDTO;
import com.derrick.cuisineconnect.services.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    @GetMapping
    public ResponseEntity<FoodResponseDTO> fetchAllFoodItems(){
        FoodResponseDTO allFoodItems = foodService.readAllFoodItems();

        return new ResponseEntity<>(allFoodItems, HttpStatus.OK);
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodResponseDTO> fetchFoodItem(@PathVariable String foodId){
        FoodResponseDTO foodItem = foodService.readFoodItemById(Long.valueOf(foodId));
        return new ResponseEntity<>(foodItem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FoodResponseDTO> addNewFoodItem(@ModelAttribute FoodRequestDTO foodItem){
        FoodResponseDTO newFoodItem = foodService.saveFoodItem(foodItem);

        if(Objects.equals(newFoodItem.getCode(), "002")){
            return new ResponseEntity<>(newFoodItem, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(newFoodItem, HttpStatus.CREATED);
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<FoodResponseDTO> updateFoodItem(@PathVariable String foodId, @ModelAttribute FoodRequestDTO foodItem){
        FoodResponseDTO foodItemToBeUpdated = foodService.updateFoodItem(Long.valueOf(foodId), foodItem);
        if(Objects.equals(foodItemToBeUpdated.getCode(), "003")){
            return new ResponseEntity<>(foodItemToBeUpdated, HttpStatus.NOT_FOUND);
        }

        if(Objects.equals(foodItemToBeUpdated.getCode(), "005")){
            return new ResponseEntity<>(foodItemToBeUpdated, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(foodItemToBeUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<FoodResponseDTO> deleteFoodItemById(@PathVariable String foodId){
        FoodResponseDTO foodItemToBeDeleted = foodService.deleteFoodItemById(Long.valueOf(foodId));

        if(Objects.equals(foodItemToBeDeleted.getCode(), "003")){
            return new ResponseEntity<>(foodItemToBeDeleted, HttpStatus.NOT_FOUND);
        }

        if(Objects.equals(foodItemToBeDeleted.getCode(), "008")){
            return new ResponseEntity<>(foodItemToBeDeleted, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(foodItemToBeDeleted, HttpStatus.OK);

    }
}
