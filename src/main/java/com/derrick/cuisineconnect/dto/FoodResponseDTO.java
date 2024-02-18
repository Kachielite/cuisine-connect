package com.derrick.cuisineconnect.dto;

import com.derrick.cuisineconnect.entity.Food;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodResponseDTO {
    private String code;
    private String message;
    private Food item;
}
