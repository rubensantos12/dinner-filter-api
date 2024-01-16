package com.dinnerfinder.project.dinerfinder.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;


@Getter @Setter
@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
@Table(name = "food_types")
public class FoodTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String allergyName;

}
