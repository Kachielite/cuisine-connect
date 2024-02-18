package com.derrick.cuisineconnect.services;


import com.derrick.cuisineconnect.entity.Food;
import org.springframework.stereotype.Service;

@Service
public interface FoodService {
    void saveFoodItem(Food food);

    void updateFoodItem(Food food, Long foodId);

    void readFoodItemById(Long foodId);

    void readAllFoodItems(Long foodId);

    void deleteFoodItemById(Long foodId);

}
