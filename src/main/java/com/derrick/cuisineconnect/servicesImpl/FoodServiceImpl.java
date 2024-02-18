package com.derrick.cuisineconnect.servicesImpl;

import com.derrick.cuisineconnect.entity.Food;
import com.derrick.cuisineconnect.repository.FoodRespository;
import com.derrick.cuisineconnect.services.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    FoodRespository foodRespository;
    @Override
    public void saveFoodItem(Food food) {

    }

    @Override
    public void updateFoodItem(Food food, Long foodId) {

    }

    @Override
    public void readFoodItemById(Long foodId) {

    }

    @Override
    public void readAllFoodItems(Long foodId) {

    }

    @Override
    public void deleteFoodItemById(Long foodId) {

    }
}
