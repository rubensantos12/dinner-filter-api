package com.dinnerfinder.project.dinerfinder.repository;

import com.dinnerfinder.project.dinerfinder.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByName(String name);
}
