package com.health.pramod.HealthCare.dto;


import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class VerificationRequest {
    private String email;
    private String otpCode;
    private String oldPassword;
    private String newPassword;




}
