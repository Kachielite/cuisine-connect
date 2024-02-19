package com.derrick.cuisineconnect.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class FoodImageRequestDTO {
    private Long foodId;
    private List<MultipartFile> images;
}
