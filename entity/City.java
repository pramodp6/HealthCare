package com.health.pramod.HealthCare.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;


    @NotBlank(message = "City Name is required")
    private String name;

    @ManyToOne
    @Min(value = 1, message = "StateId must be greater than zero")
    @JoinColumn(name = "state_id",nullable = false)
    private State state;


}
