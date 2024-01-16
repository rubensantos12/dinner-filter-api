package com.dinnerfinder.project.dinerfinder.service;

import com.dinnerfinder.project.dinerfinder.entity.FoodTypes;
import com.dinnerfinder.project.dinerfinder.repository.FoodTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FoodTypeService {

    private final FoodTypeRepository foodTypeRepository;

    public FoodTypeService(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }

    public void addFoodTypes(FoodTypes foodTypes) {
        foodTypeRepository.save(foodTypes);
    }

    public List<FoodTypes> getAllFoodTypes() {
        return foodTypeRepository.findAll();
    }

    public void addFoodTypes(Set<FoodTypes> foodTypesSet) {
        foodTypeRepository.saveAll(foodTypesSet);
    }
}
