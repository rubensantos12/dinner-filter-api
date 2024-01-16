package com.dinnerfinder.project.dinerfinder.repository;

import com.dinnerfinder.project.dinerfinder.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findMealsByRestaurantId(Long restaurantId);

    @Modifying
    @Query("UPDATE Restaurant r SET r.meals = :meals WHERE r.id = :restaurantId")
    void addMealToRestaurant(@Param("restaurantId") Long restaurantId, @Param("meals") Set<Meal> meals);
}
