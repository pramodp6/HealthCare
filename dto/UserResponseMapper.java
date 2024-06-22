package com.health.pramod.HealthCare.dto;

import com.health.pramod.HealthCare.entity.Patients;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {
    public UserDetailsResponse toUserResponse(Patients patients) {
        UserDetailsResponse userResponse = new UserDetailsResponse();
        userResponse.setId(patients.getPatientId());
        userResponse.setFirstName(patients.getFirstname());
        userResponse.setLastName(patients.getLastname());
        userResponse.setEmail(patients.getEmail());
        userResponse.setGender(patients.getGender());
        userResponse.setAddress(patients.getAddress());
        userResponse.setRole(patients.getRole().toString());
        return userResponse;
    }
}
