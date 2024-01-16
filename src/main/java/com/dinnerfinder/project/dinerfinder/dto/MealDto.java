package com.dinnerfinder.project.dinerfinder.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MealDto {

    private String mealName;
    private String description;
}
