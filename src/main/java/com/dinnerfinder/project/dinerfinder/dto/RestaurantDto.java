package com.dinnerfinder.project.dinerfinder.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
public class RestaurantDto {

    private String name;
    private String address;
    private String country;
    private String rating;
    private Set<MealDto> meals = new HashSet<>();
    private Set<FoodTypesDto> foodTypes = new HashSet<>();
}
