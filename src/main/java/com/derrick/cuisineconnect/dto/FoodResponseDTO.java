package com.derrick.cuisineconnect.dto;

import com.derrick.cuisineconnect.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodResponseDTO {
    private String code;
    private String message;
    private Food item;
    private List<Food> items;
}
