package com.dinnerfinder.project.dinerfinder.mappers;

import com.dinnerfinder.project.dinerfinder.dto.MealDto;
import com.dinnerfinder.project.dinerfinder.entity.Meal;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MealMapper {

    private MealMapper() {
        //
    }

    public static List<MealDto> mapToDtoList(Set<Meal> mealSet) {
        return mealSet.stream()
                .map(MealMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static List<MealDto> mapToDtoList(List<Meal> mealSet) {
        return mealSet.stream()
                .map(MealMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static Set<MealDto> mapToDtoSet(Set<Meal> mealSet) {
        return mealSet.stream()
                .map(MealMapper::mapToDto)
                .collect(Collectors.toSet());
    }

    public static MealDto mapToDto(Meal meal) {
        return MealDto.builder()
                .description(meal.getDescription())
                .mealName(meal.getMealName())
                .build();
    }

    public static Meal mapToEntity(MealDto mealDto) {
        Meal meal = new Meal();
        meal.setMealName(mealDto.getMealName());
        meal.setDescription(mealDto.getDescription());
        return meal;
    }

    public static List<Meal> mapToEntityList(Set<MealDto> mealDtoSet) {
        return mealDtoSet.stream()
                .map(MealMapper::mapToEntity)
                .collect(Collectors.toList());
    }

    public static Set<Meal> mapToEntitySet(Set<MealDto> mealDtoSet) {
        return mealDtoSet.stream()
                .map(MealMapper::mapToEntity)
                .collect(Collectors.toSet());
    }
}
