package com.dinnerfinder.project.dinerfinder.service;

import com.dinnerfinder.project.dinerfinder.dto.RestaurantDto;
import com.dinnerfinder.project.dinerfinder.entity.Restaurant;
import com.dinnerfinder.project.dinerfinder.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {


    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        return restaurantOptional.orElse(null);
    }

    public void removeRestaurantById(Long restaurantId) {
            restaurantRepository.deleteById(restaurantId);
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void addRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = Restaurant.builder()
                .address(restaurantDto.getAddress())
                .name(restaurantDto.getName())
                .country(restaurantDto.getCountry())
                .rating(restaurantDto.getRating())
                .build();
        restaurantRepository.save(restaurant);
    }


    public Optional<Restaurant> getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }
}
