package com.dinnerfinder.project.dinerfinder.mappers;

import com.dinnerfinder.project.dinerfinder.dto.FoodTypesDto;
import com.dinnerfinder.project.dinerfinder.entity.FoodTypes;

import java.util.Set;
import java.util.stream.Collectors;

public class FoodTypesMapper {

    private FoodTypesMapper() {
        //
    }

    public static Set<FoodTypesDto> mapToDtoSet(Set<FoodTypes> foodTypesSet) {
        return foodTypesSet.stream()
            .map(FoodTypesMapper::mapToDto)
            .collect(Collectors.toSet());
    }

    public static FoodTypesDto mapToDto(FoodTypes foodTypes) {
        return FoodTypesDto.builder()
                .typeName(foodTypes.getAllergyName())
                .build();
    }

    public static FoodTypes mapToEntity(FoodTypesDto foodTypesDTO) {
        FoodTypes foodTypes = new FoodTypes();
        foodTypes.setAllergyName(foodTypesDTO.getTypeName());
        return foodTypes;
    }

    public static Set<FoodTypes> mapToEntitySet(Set<FoodTypesDto> foodTypesDTOSet) {
        return foodTypesDTOSet.stream()
                .map(FoodTypesMapper::mapToEntity)
                .collect(Collectors.toSet());
    }

}