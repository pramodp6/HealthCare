package com.health.pramod.HealthCare.dto;

import lombok.Data;

@Data
public class UserDetailsResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String role;
}
