package com.derrick.cuisineconnect.repository;

import com.derrick.cuisineconnect.entity.Food;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query("SELECT f FROM Food f LEFT JOIN FETCH f.foodImageList WHERE f.id = :foodId")
    Optional<Food> findFoodWithImagesById(Long foodId);
}
