package com.derrick.cuisineconnect.services;


import com.derrick.cuisineconnect.dto.FoodRequestDTO;
import com.derrick.cuisineconnect.dto.FoodResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface FoodService {
    FoodResponseDTO saveFoodItem(FoodRequestDTO food);

    FoodResponseDTO updateFoodItem(Long foodId, FoodRequestDTO request);

    FoodResponseDTO readFoodItemById(Long foodId);

    FoodResponseDTO readAllFoodItems();

    FoodResponseDTO deleteFoodItemById(Long foodId);

}
