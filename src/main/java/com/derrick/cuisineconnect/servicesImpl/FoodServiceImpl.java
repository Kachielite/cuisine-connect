package com.derrick.cuisineconnect.servicesImpl;


import com.derrick.cuisineconnect.dto.FoodRequestDTO;
import com.derrick.cuisineconnect.dto.FoodResponseDTO;
import com.derrick.cuisineconnect.entity.Categories;
import com.derrick.cuisineconnect.entity.Food;
import com.derrick.cuisineconnect.entity.FoodImage;
import com.derrick.cuisineconnect.repository.FoodImageRespository;
import com.derrick.cuisineconnect.repository.FoodRepository;
import com.derrick.cuisineconnect.services.CloudinaryService;
import com.derrick.cuisineconnect.services.FoodService;
import com.derrick.cuisineconnect.utils.FoodUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final FoodImageRespository foodImageRespository;
    private final CloudinaryService cloudinaryService;


    @Override
    public FoodResponseDTO saveFoodItem(FoodRequestDTO request) {

        try{
            Food newFood = Food.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(request.getPrice())
                    .category(Categories.valueOf(request.getCategory()))
                    .build();

            Food savedFoodItem = foodRepository.save(newFood);

            if(request.getImages() != null && !request.getImages().isEmpty()){
                for(MultipartFile image: request.getImages()){
                    // Ensure the file is not empty before processing
                    if (!image.isEmpty()) {
                        String foodImageUrl = cloudinaryService.uploadImage(image, "food-image");
                        FoodImage foodImage = FoodImage.builder()
                                .imageUrl(foodImageUrl)
                                .food(savedFoodItem)
                                .build();
                        foodImageRespository.save(foodImage);
                    }
                }
            }



            return FoodResponseDTO.builder()
                    .code(FoodUtils.FOOD_ITEM_ADDED_SUCCESSFULLY_CODE)
                    .message(FoodUtils.FOOD_ITEM_ADDED_SUCCESSFULLY_MESSAGE)
                    .item(savedFoodItem)
                    .build();
        } catch (IOException e) {
            return FoodResponseDTO.builder()
                    .code(FoodUtils.FOOD_ITEM_ADDED_FAILED_CODE)
                    .message(FoodUtils.FOOD_ITEM_ADDED_FAILED_MESSAGE + "--->" + e.getMessage())
                    .item(null)
                    .build();
        }

    }

    @Override
    public FoodResponseDTO updateFoodItem(Long foodId, FoodRequestDTO request) {
       Optional<Food> foodItem = foodRepository.findById(foodId);

       try{
           if(foodItem.isEmpty()){
               return FoodResponseDTO.builder()
                       .code(FoodUtils.FOOD_ITEM_ADDED_NOT_FOUND_CODE)
                       .message(FoodUtils.FOOD_ITEM_ADDED_NOT_FOUND_MESSAGE)
                       .item(null)
                       .build();
           }

           Food footItemToBeUpdated = foodItem.get();
           footItemToBeUpdated.setName(request.getName());
           footItemToBeUpdated.setDescription(request.getDescription());
           footItemToBeUpdated.setPrice(request.getPrice());
           footItemToBeUpdated.setCategory(Categories.valueOf(request.getCategory()));

           foodRepository.save(footItemToBeUpdated);

           return FoodResponseDTO.builder()
                   .code(FoodUtils.FOOD_ITEM_UPDATED_SUCCESSFULLY_CODE)
                   .message(FoodUtils.FOOD_ITEM_UPDATED_SUCCESSFULLY_MESSAGE)
                   .item(footItemToBeUpdated)
                   .build();
       } catch (IllegalArgumentException e) {
           return FoodResponseDTO.builder()
                   .code(FoodUtils.FOOD_ITEM_UPDATED_FAILED_CODE)
                   .message(FoodUtils.FOOD_ITEM_UPDATED_FAILED_MESSAGE + "--->" + e.getMessage())
                   .item(null)
                   .build();
       }


    }

    @Override
    public FoodResponseDTO readFoodItemById(Long foodId) {
        Optional<Food> foodItem = foodRepository.findFoodWithImagesById(foodId);

        try{
            if(foodItem.isEmpty()){
                return FoodResponseDTO.builder()
                        .code(FoodUtils.FOOD_ITEM_ADDED_NOT_FOUND_CODE)
                        .message(FoodUtils.FOOD_ITEM_ADDED_NOT_FOUND_MESSAGE)
                        .item(null)
                        .build();
            }



            return FoodResponseDTO.builder()
                    .code(FoodUtils.FOOD_ITEM_RETRIEVED_CODE)
                    .message(FoodUtils.FOOD_ITEM_RETRIEVE_MESSAGE)
                    .item(foodItem.get())
                    .build();
        } catch (Exception e) {
            return FoodResponseDTO.builder()
                    .code(FoodUtils.FOOD_ITEM_UPDATED_FAILED_CODE)
                    .message(FoodUtils.FOOD_ITEM_UPDATED_FAILED_MESSAGE + "--->" + e.getMessage())
                    .item(null)
                    .build();
        }

    }

    @Override
    public FoodResponseDTO readAllFoodItems() {
        List<Food> foodItemList = foodRepository.findAll();

        return FoodResponseDTO.builder()
                .code(FoodUtils.FOOD_ITEM_RETRIEVED_CODE)
                .message(FoodUtils.FOOD_ITEM_RETRIEVE_MESSAGE)
                .item((Food) foodItemList)
                .build();

    }

    @Override
    public FoodResponseDTO deleteFoodItemById(Long foodId) {
        Optional<Food> foodItem = foodRepository.findById(foodId);

        try{
            if(foodItem.isEmpty()){
                return FoodResponseDTO.builder()
                        .code(FoodUtils.FOOD_ITEM_ADDED_NOT_FOUND_CODE)
                        .message(FoodUtils.FOOD_ITEM_ADDED_NOT_FOUND_MESSAGE)
                        .item(null)
                        .build();
            }

            foodRepository.deleteById(foodId);

            return FoodResponseDTO.builder()
                    .code(FoodUtils.FOOD_ITEM_DELETED_CODE)
                    .message(FoodUtils.FOOD_ITEM_DELETED_MESSAGE)
                    .item(foodItem.get())
                    .build();
        } catch (Exception e) {
            return FoodResponseDTO.builder()
                    .code(FoodUtils.FOOD_ITEM_DELETION_FAILED_CODE)
                    .message(FoodUtils.FOOD_ITEM_DELETION_FAILED_MESSAGE + "--->" + e.getMessage())
                    .item(null)
                    .build();
        }

    }
}
