package com.derrick.cuisineconnect.repository;

import com.derrick.cuisineconnect.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRespository extends JpaRepository<Food, Long> {
}
