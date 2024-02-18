package com.derrick.cuisineconnect.repository;

import com.derrick.cuisineconnect.entity.Food;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FoodRepository extends JpaRepository<Food, Long> {

}
