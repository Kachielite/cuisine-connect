package com.derrick.cuisineconnect.servicesImpl;

import com.derrick.cuisineconnect.dto.FoodImageRequestDTO;
import com.derrick.cuisineconnect.dto.FoodImageResponseDTO;
import com.derrick.cuisineconnect.dto.FoodRequestDTO;
import com.derrick.cuisineconnect.entity.Food;
import com.derrick.cuisineconnect.entity.FoodImage;
import com.derrick.cuisineconnect.repository.FoodImageRespository;
import com.derrick.cuisineconnect.repository.FoodRepository;
import com.derrick.cuisineconnect.services.CloudinaryService;
import com.derrick.cuisineconnect.services.FoodImageService;
import com.derrick.cuisineconnect.utils.FoodUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodImageServiceImpl implements FoodImageService {
    private final FoodRepository foodRepository;
    private final FoodImageRespository foodImageRespository;
    private final CloudinaryService cloudinaryService;
    @Override
    public FoodImageResponseDTO addImage(FoodImageRequestDTO imagesRequest) {
        Optional<Food> foodItem = foodRepository.findById(imagesRequest.getFoodId());

        try{
            if(foodItem.isEmpty()){
                return FoodImageResponseDTO.builder()
                        .code(FoodUtils.FOOD_IMAGE_ASSOCIATED_WITH_FOOD_ID_NOT_FOUND_CODE)
                        .message(FoodUtils.FOOD_IMAGE_ASSOCIATED_WITH_FOOD_ID_NOT_FOUND_MESSAGE)
                        .images(null)
                        .build();
            }

            if(!imagesRequest.getImages().isEmpty()){
                for(MultipartFile image: imagesRequest.getImages()){
                    String foodImageUrl = cloudinaryService.uploadImage(image, "food-image");
                    FoodImage foodImage = FoodImage.builder()
                            .imageUrl(foodImageUrl)
                            .food(foodItem.get())
                            .build();
                    foodImageRespository.save(foodImage);
                }
            }

            FoodImage foodImages = (FoodImage) foodImageRespository.findFoodImagesById(imagesRequest.getFoodId());

            return FoodImageResponseDTO.builder()
                    .code(FoodUtils.FOOD_IMAGE_SAVED_CODE)
                    .message(FoodUtils.FOOD_IMAGE_SAVED_MESSAGE)
                    .images(String.valueOf(foodImages))
                    .build();


        } catch (IOException e) {
            return FoodImageResponseDTO.builder()
                    .code(FoodUtils.FOOD_IMAGE_SAVED_FAILED_CODE)
                    .message(FoodUtils.FOOD_IMAGE_SAVED_FAILED_MESSAGE + " " + e.getMessage())
                    .images(null)
                    .build();
        }
    }

    @Override
    public FoodImageResponseDTO deleteImageById(Long foodImageId) {
        Optional<FoodImage> foodImage = foodImageRespository.findById(foodImageId);

        try{
            if(foodImage.isEmpty()){
                return FoodImageResponseDTO.builder()
                        .code(FoodUtils.FOOD_IMAGE_NOT_FOUND_CODE)
                        .message(FoodUtils.FOOD_IMAGE_NOT_FOUND_MESSAGE)
                        .images(null)
                        .build();
            }

            foodImageRespository.deleteById(foodImageId);

            return FoodImageResponseDTO.builder()
                    .code(FoodUtils.FOOD_IMAGE_DELETED_CODE)
                    .message(FoodUtils.FOOD_IMAGE_DELETED_CODE_MESSAGE)
                    .images(null)
                    .build();
        } catch (Exception e) {
            return FoodImageResponseDTO.builder()
                    .code(FoodUtils.FOOD_IMAGE_FAILED_TO_DELETE_CODE)
                    .message(FoodUtils.FOOD_IMAGE_FAILED_TO_DELETE_MESSAGE + " " + e.getMessage())
                    .images(null)
                    .build();
        }
    }
}
