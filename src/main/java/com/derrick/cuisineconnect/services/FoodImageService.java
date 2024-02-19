package com.derrick.cuisineconnect.services;

import com.derrick.cuisineconnect.dto.FoodImageRequestDTO;
import com.derrick.cuisineconnect.dto.FoodImageResponseDTO;
import com.derrick.cuisineconnect.dto.FoodRequestDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface FoodImageService {
    FoodImageResponseDTO addImage(FoodImageRequestDTO images) throws IOException;
    FoodImageResponseDTO deleteImageById(Long foodImageId);
}
