package com.derrick.cuisineconnect.controllers;

import com.derrick.cuisineconnect.dto.FoodImageRequestDTO;
import com.derrick.cuisineconnect.dto.FoodImageResponseDTO;
import com.derrick.cuisineconnect.services.FoodImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;


@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class FoodImageController {
    FoodImageService foodImageService;
    @PutMapping
    public ResponseEntity<FoodImageResponseDTO> addImage(@RequestBody FoodImageRequestDTO imagesRequest) throws IOException {
        FoodImageResponseDTO foodImages = foodImageService.addImage(imagesRequest);

        if(Objects.equals(foodImages.getCode(), "009")){
            return new ResponseEntity<>(foodImages, HttpStatus.NOT_FOUND);
        }

        if(Objects.equals(foodImages.getCode(), "011")){
            return new ResponseEntity<>(foodImages, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(foodImages, HttpStatus.CREATED);
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<FoodImageResponseDTO> deleteImage(@PathVariable String foodId){
        FoodImageResponseDTO foodImageToBeDeleted = foodImageService.deleteImageById(Long.valueOf(foodId));

        if(Objects.equals(foodImageToBeDeleted.getCode(), "012")){
            return new ResponseEntity<>(foodImageToBeDeleted, HttpStatus.NOT_FOUND);
        }

        if(Objects.equals(foodImageToBeDeleted.getCode(), "014")){
            return new ResponseEntity<>(foodImageToBeDeleted, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(foodImageToBeDeleted, HttpStatus.OK);
    }


}
