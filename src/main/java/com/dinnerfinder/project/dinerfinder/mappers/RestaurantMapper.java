package com.dinnerfinder.project.dinerfinder.mappers;

import com.dinnerfinder.project.dinerfinder.dto.RestaurantDto;
import com.dinnerfinder.project.dinerfinder.entity.Restaurant;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantMapper {

    private RestaurantMapper() {
        //
    }

    public static List<RestaurantDto> mapToDtoList(Set<Restaurant> restaurantSet) {
        return restaurantSet.stream()
                .map(RestaurantMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static List<RestaurantDto> mapToDtoList(List<Restaurant> restaurantSet) {
        return restaurantSet.stream()
                .map(RestaurantMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static RestaurantDto mapToDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .address(restaurant.getAddress())
                .meals(MealMapper.mapToDtoSet(restaurant.getMeals()))
                .rating(restaurant.getRating())
                .name(restaurant.getName())
                .country(restaurant.getCountry())
                .foodTypes(FoodTypesMapper.mapToDtoSet(restaurant.getFoodTypes()))
                .build();
    }

    public static Restaurant mapToEntity(RestaurantDto restaurantDto) {
        return Restaurant.builder()
                .rating(restaurantDto.getRating())
                .name(restaurantDto.getName())
                .country(restaurantDto.getCountry())
                .address(restaurantDto.getAddress())
                .build();
    }

    public static List<Restaurant> mapToEntityList(Set<RestaurantDto> restaurantDtoSet) {
        return restaurantDtoSet.stream()
                .map(RestaurantMapper::mapToEntity)
                .collect(Collectors.toList());
    }
}
