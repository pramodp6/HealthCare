package com.health.pramod.HealthCare.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "states")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int state_id;
    @NotBlank(message = "State Name is required")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "country_id",nullable = false)
    @Min(value = 1, message = "Country Id must be greater than zero")
    private Country country;



}
