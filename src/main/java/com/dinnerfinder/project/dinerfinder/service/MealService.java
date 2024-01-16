package com.dinnerfinder.project.dinerfinder.service;

import com.dinnerfinder.project.dinerfinder.entity.Meal;
import com.dinnerfinder.project.dinerfinder.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public void addMeal(Meal meal) {
        mealRepository.save(meal);
    }

    public List<Meal> getMealsByRestaurant(Long restaurantId) {
        return mealRepository.findMealsByRestaurantId(restaurantId);
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public void addMeals(Set<Meal> meals) {
        mealRepository.saveAll(meals);
    }
}
