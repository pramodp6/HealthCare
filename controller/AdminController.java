package com.health.pramod.HealthCare.controller;

import com.health.pramod.HealthCare.dto.ApiResponse;
import com.health.pramod.HealthCare.dto.ApiResponseError;
import com.health.pramod.HealthCare.dto.PaitientDto;
import com.health.pramod.HealthCare.dto.VerificationRequest;
import com.health.pramod.HealthCare.entity.Patients;
import com.health.pramod.HealthCare.repository.PatientsRepository;
import com.health.pramod.HealthCare.services.AdminService;
import com.health.pramod.HealthCare.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private  AdminService adminService;

    @GetMapping("/patient")
    public ResponseEntity<?> patientAllData(){
      List<PaitientDto> u = adminService.allPatientData();
        return ResponseEntity.badRequest().body(new ApiResponse(true, "Patient Successfully Found!",u));

    }


    @PostMapping("/hello")
    public String tata(){
        return "Hello";
    }


    @PostMapping("/createDoctor")
    public ResponseEntity<?> userRegistration(@RequestBody PaitientDto paitientCreateDto) {
        System.out.print(paitientCreateDto.getPassword());

        boolean patients = adminService.createDoctor(paitientCreateDto);
        if(patients){
            return ResponseEntity.ok().body(new ApiResponse(patients, "Register Record SucessFully verification sent Email!",null));
        }else {
            return ResponseEntity.badRequest().body(new ApiResponseError(patients, "already Email Exists!"));

        }

    }
    @PostMapping("/verifyEmail")
    public ResponseEntity<?> verifyUser(@RequestBody VerificationRequest requestCode){
        if (adminService.verifyPatient(requestCode.getEmail(),requestCode.getOtpCode())) {
            return ResponseEntity.ok(new ApiResponse(true, "Email verification successful.", null));

        } else {
            return ResponseEntity.badRequest().body(new ApiResponseError(true, "Invalid verification code."));
        }
    }
}
