package com.dinnerfinder.project.dinerfinder.repository;

import com.dinnerfinder.project.dinerfinder.entity.FoodTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodTypeRepository extends JpaRepository<FoodTypes, Long> {

    @Query("SELECT f FROM FoodTypes f WHERE f.allergyName = :allergyName")
    FoodTypes findByName(@Param("allergyName") String allergyName);
}
