package com.derrick.cuisineconnect.controllers;

import com.derrick.cuisineconnect.dto.FoodRequestDTO;
import com.derrick.cuisineconnect.dto.FoodResponseDTO;
import com.derrick.cuisineconnect.services.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/meal")
@RequiredArgsConstructor
@Tag(name = "Meals")
public class FoodController {
    private final FoodService foodService;
    @Operation(
            description = "Get all meals",
            summary = "This endpoint returns all the meals",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<FoodResponseDTO> fetchAllFoodItems(){
        FoodResponseDTO allFoodItems = foodService.readAllFoodItems();

        return new ResponseEntity<>(allFoodItems, HttpStatus.OK);
    }

    @Operation(
            description = "Get meal by id",
            summary = "This endpoint returns the meal with the requested id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/{mealId}")
    public ResponseEntity<FoodResponseDTO> fetchFoodItem(@PathVariable String mealId){
        FoodResponseDTO foodItem = foodService.readFoodItemById(Long.valueOf(mealId));
        return new ResponseEntity<>(foodItem, HttpStatus.OK);
    }


    @Operation(
            description = "Add new meal",
            summary = "This endpoint adds a new meal item",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<FoodResponseDTO> addNewFoodItem(@ModelAttribute FoodRequestDTO foodItem){
        FoodResponseDTO newFoodItem = foodService.saveFoodItem(foodItem);

        if(Objects.equals(newFoodItem.getCode(), "002")){
            return new ResponseEntity<>(newFoodItem, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(newFoodItem, HttpStatus.CREATED);
    }


    @Operation(
            description = "Update meal",
            summary = "This endpoint updates the meal with the provided ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/{mealId}")
    public ResponseEntity<FoodResponseDTO> updateFoodItem(@PathVariable String mealId, @ModelAttribute FoodRequestDTO foodItem){
        FoodResponseDTO foodItemToBeUpdated = foodService.updateFoodItem(Long.valueOf(mealId), foodItem);
        if(Objects.equals(foodItemToBeUpdated.getCode(), "003")){
            return new ResponseEntity<>(foodItemToBeUpdated, HttpStatus.NOT_FOUND);
        }

        if(Objects.equals(foodItemToBeUpdated.getCode(), "005")){
            return new ResponseEntity<>(foodItemToBeUpdated, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(foodItemToBeUpdated, HttpStatus.OK);
    }


    @Operation(
            description = "Deletes meal",
            summary = "This endpoint deletes the meal with the provided ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404"
                    )
            }
    )
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
