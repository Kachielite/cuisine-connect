package com.derrick.cuisineconnect.repository;

import com.derrick.cuisineconnect.entity.FoodImage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FoodImageRespository extends JpaRepository<FoodImage, Long> {
    List<FoodImage> findFoodImagesById(Long foodId);
}
