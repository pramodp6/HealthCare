package com.health.pramod.HealthCare.dto;


import lombok.Data;

@Data
public class LoginReponse {
    private String token;
    private UserDetailsResponse user;
}
