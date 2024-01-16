package com.dinnerfinder.project.dinerfinder.controller;

import com.dinnerfinder.project.dinerfinder.dto.MealDto;
import com.dinnerfinder.project.dinerfinder.dto.RestaurantDto;
import com.dinnerfinder.project.dinerfinder.entity.Meal;
import com.dinnerfinder.project.dinerfinder.entity.Restaurant;
import com.dinnerfinder.project.dinerfinder.mappers.MealMapper;
import com.dinnerfinder.project.dinerfinder.mappers.RestaurantMapper;
import com.dinnerfinder.project.dinerfinder.service.MealService;
import com.dinnerfinder.project.dinerfinder.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class RestaurantController {


    private final RestaurantService restaurantService;
    private final MealService restaurantMealsService;


    public RestaurantController(RestaurantService restaurantService, MealService restaurantMealsService) {
        this.restaurantService = restaurantService;
        this.restaurantMealsService = restaurantMealsService;
    }

    @GetMapping("/getAllRestaurants")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        try {
            List<Restaurant> restaurants = restaurantService.getRestaurants();
            return new ResponseEntity<>(RestaurantMapper.mapToDtoList(restaurants), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRestaurantById/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable(name = "id") Long id) {

        Restaurant restaurant = restaurantService.getRestaurantById(id);

        if (restaurant == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(RestaurantMapper.mapToDto(restaurant), HttpStatus.OK);
    }

    @GetMapping("/getRestaurantByName/{name}")
    public ResponseEntity<RestaurantDto> getRestaurantByName(@PathVariable(name = "name") String name) {

        Optional<Restaurant> restaurant = restaurantService.getRestaurantByName(name);

        return restaurant.map(value -> new ResponseEntity<>(RestaurantMapper.mapToDto(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/removeRestaurantById/{id}")
    public ResponseEntity<String> removeRestaurantById(@PathVariable(name = "id") Long id) {
        try {
            restaurantService.removeRestaurantById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid ID", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<String> addRestaurantToDb(@RequestBody RestaurantDto restaurant) {
            try{
                restaurantService.getRestaurantByName(restaurant.getName());
                restaurantService.addRestaurant(restaurant);
            }catch (Exception e){
                return new ResponseEntity<>("Invalid restaurant body", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/meals/{restaurantId}")
    public ResponseEntity<List<MealDto>> getMealsByRestaurant(@PathVariable Long restaurantId) {
        try{
            List<Meal> mealList = restaurantMealsService.getMealsByRestaurant(restaurantId);
            return new ResponseEntity<>(MealMapper.mapToDtoList(mealList), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/restaurant/meals/addMeal/{restaurantId}")
    public ResponseEntity<String> addMealToRestaurantById(@PathVariable Long restaurantId, @RequestBody Set<MealDto> mealDtoSet) {
        try {
            Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

            Set<Meal> meals = mealDtoSet.stream()
                    .map(this::convertToMealEntity)
                    .collect(Collectors.toSet());

            for (Meal meal : meals) {
                meal.setRestaurant(restaurant); // Set the reference to the Restaurant
            }

            restaurantMealsService.addMeals(MealMapper.mapToEntitySet(mealDtoSet));

            restaurant.getMeals().addAll(meals);
            restaurantService.addRestaurant(restaurant);
            return new ResponseEntity<>("Meal added to restaurant successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding meal to restaurant", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Meal convertToMealEntity(MealDto mealDto) {
        return new Meal(mealDto.getMealName(), mealDto.getDescription());
    }

}
