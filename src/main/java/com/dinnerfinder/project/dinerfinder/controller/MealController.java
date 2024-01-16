package com.dinnerfinder.project.dinerfinder.controller;

import com.dinnerfinder.project.dinerfinder.dto.MealDto;
import com.dinnerfinder.project.dinerfinder.entity.Meal;
import com.dinnerfinder.project.dinerfinder.mappers.MealMapper;
import com.dinnerfinder.project.dinerfinder.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/meals/getAll")
    public ResponseEntity<List<MealDto>> getAllMeals() {
        try{
            List<Meal> listOfMeals = mealService.getAllMeals();
            return new ResponseEntity<>(MealMapper.mapToDtoList(new HashSet<>(listOfMeals)) ,HttpStatus.OK) ;
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
