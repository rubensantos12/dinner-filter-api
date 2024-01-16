package com.dinnerfinder.project.dinerfinder.controller;

import com.dinnerfinder.project.dinerfinder.commons.FoodTypesEnum;
import com.dinnerfinder.project.dinerfinder.dto.FoodTypesDto;
import com.dinnerfinder.project.dinerfinder.entity.FoodTypes;
import com.dinnerfinder.project.dinerfinder.entity.Restaurant;
import com.dinnerfinder.project.dinerfinder.service.FoodTypeService;
import com.dinnerfinder.project.dinerfinder.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FoodTypeController {

    private final FoodTypeService foodTypeService;
    private final RestaurantService restaurantService;

    public FoodTypeController(FoodTypeService foodTypeService, RestaurantService restaurantService) {
        this.foodTypeService = foodTypeService;
        this.restaurantService = restaurantService;
    }

    @PostMapping(value = "/foodTypes/add")
    public ResponseEntity<String> addFoodTypesToDb(@RequestBody FoodTypesDto foodTypesDto) {
        FoodTypes foodTypes = FoodTypes.builder()
                .allergyName(foodTypesDto.getTypeName())
                .build();
        try{
            foodTypeService.addFoodTypes(foodTypes);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid Food Type", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Added successfully!", HttpStatus.OK);
    }

    @PostMapping("/restaurant/addFoodTypeToRestaurantById/{restaurantId}")
    public ResponseEntity<String> addFoodTypeToRestaurantById(@PathVariable Long restaurantId, @RequestBody Set<FoodTypesDto> foodTypesDtoSet) {

        if(validateFoodType(foodTypesDtoSet)) {
            try {
                Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

                Set<FoodTypes> foodTypes = foodTypesDtoSet.stream()
                        .map(this::convertToFoodTypeEntity)
                        .collect(Collectors.toSet());
                foodTypeService.addFoodTypes(foodTypes);

                restaurant.getFoodTypes().addAll(foodTypes);
                restaurantService.addRestaurant(restaurant);
                return new ResponseEntity<>("Food Type added to restaurant successfully", HttpStatus.CREATED);
            } catch (IllegalArgumentException exception) {
                return new ResponseEntity<>("Invalid restaurant ID", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } return new ResponseEntity<>("Invalid Food Type specified - Valid Food Types:" + Arrays.toString(FoodTypesEnum.values()), HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value = "/foodTypes/getAll")
    public ResponseEntity<List<FoodTypes>> getAllFoodTypes(){
        try{
            return new ResponseEntity<>(foodTypeService.getAllFoodTypes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    private FoodTypes convertToFoodTypeEntity(FoodTypesDto foodTypesDto) {
        return new FoodTypes(foodTypesDto.getTypeName());
    }

    public boolean validateFoodType(Set<FoodTypesDto> foodType) {
        List<FoodTypesDto> foodTypes = foodType.stream().toList();
        for(FoodTypesEnum foodTypesEnum : FoodTypesEnum.values()){
            for(FoodTypesDto foodTypesList : foodTypes) {
                if (foodTypesEnum.name().equals(foodTypesList.getTypeName().toUpperCase())) {
                    return true;
                }
            }
        }
        return false;
    }

}
