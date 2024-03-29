package com.derrick.cuisineconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequestDTO {
    private String name;
    private String description;
    private Integer price;
    private String category;
    private List<MultipartFile> images;
}
