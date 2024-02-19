package com.derrick.cuisineconnect.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodImageResponseDTO {
    private String code;
    private String message;
    private String images;
}
