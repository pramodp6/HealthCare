package com.health.pramod.HealthCare.controller;



import com.health.pramod.HealthCare.dto.*;
import com.health.pramod.HealthCare.entity.Patients;
import com.health.pramod.HealthCare.exception.UserNotFoundException;
import com.health.pramod.HealthCare.exception.VerificationLimitExceededException;
import com.health.pramod.HealthCare.services.PatientService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health")
public class PatientController {
    @Autowired
    private PatientService patientServices;

    @PostMapping("/createUser")
    public ResponseEntity<?> userRegistration(@RequestBody PaitientDto paitientCreateDto) {

        boolean patients = patientServices.createPatient(paitientCreateDto);
            if(patients){
                return ResponseEntity.ok().body(new ApiResponse(patients, "Register Record SucessFully verification sent Email!",null));
            }else {
                return ResponseEntity.badRequest().body(new ApiResponseError(patients, "already Email Exists!"));

            }

    }




    @PostMapping("/resendMail")
    public ResponseEntity<?> resendMail(@RequestParam String email) {
        boolean isMailResent = patientServices.resendMail(email);
        if (isMailResent) {
            return ResponseEntity.ok().body(new ApiResponse(isMailResent, "Mail resent successfully!",null));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponseError(isMailResent, "InterNal Error!"));
        }
    }




    @PostMapping("/verifyEmail")
    public ResponseEntity<?> verifyUser(@RequestBody VerificationRequest requestCode){
        if (patientServices.verifyPatient(requestCode.getEmail(),requestCode.getOtpCode())) {
            return ResponseEntity.ok(new ApiResponse(true, "Email verification successful.", null));

        } else {
            return ResponseEntity.badRequest().body(new ApiResponseError(true, "Invalid verification code."));
        }
    }



















    @PatchMapping("/updatePatientDetails/{id}")
    public ResponseEntity<?>patientUpdateDetails(@PathVariable Long id,@RequestBody PaitientDto paitientDto) {
        try {
            PaitientDto updated = patientServices.UpdateDetailsPatient(id, paitientDto);
            if (updated !=null) {
                return ResponseEntity.ok().body(new ApiResponse(true, "Update Record SucessFully!",updated));

            } else {
                return ResponseEntity.badRequest().body(new ApiResponseError(false, "User Not Found!"));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseError(false, e.getMessage()));
        }
    }




    @PostMapping("/forgetotp")
    public ResponseEntity<?> forgetPassword(@RequestParam String email) {
        boolean isMailResent = patientServices.forgotAccount(email);
        if (isMailResent) {
            return ResponseEntity.ok().body(new ApiResponse(isMailResent, "Forgot Otp Send Emaill check..!",null));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponseError(isMailResent, "InterNal Error!"));
        }
    }


    @PostMapping("/otpForgetResend")
    public ResponseEntity<?> forgetResendPassword(@RequestParam String email) {
        boolean isMailResent = patientServices.forgetOtpresend(email);
        if (isMailResent) {
            return ResponseEntity.ok().body(new ApiResponse(isMailResent, "Forgot Otp Send Emaill check..!",null));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponseError(isMailResent, "InterNal Error!"));
        }
    }

    @PostMapping("/verifyFortgot")
    public ResponseEntity<?> verifyforGotOtp(@RequestBody VerificationRequest requestCode){
        if (patientServices.verifyforgotOtp(requestCode.getEmail(),requestCode.getOtpCode(),requestCode.getNewPassword())) {
            return ResponseEntity.ok(new ApiResponse(true, "Password Forget  successful.", null));

        } else {
            return ResponseEntity.badRequest().body(new ApiResponseError(true, "Invalid verification code."));
        }
    }
}

