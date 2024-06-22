package com.health.pramod.HealthCare.controller;


import com.health.pramod.HealthCare.dto.*;
import com.health.pramod.HealthCare.entity.Patients;
import com.health.pramod.HealthCare.security.JwtHelper;
import com.health.pramod.HealthCare.services.PatientService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/authenticate")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private UserDetailsService userDetailsService;
    private PatientService patientService;

    private AuthenticationManager manager;
    @Autowired
    private UserResponseMapper userResponseMapper;
    private JwtHelper helper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PaitientDto request){
       try {


      doAuthenticate(request.getUsername(), request.getPassword());
      UserDetails userDetails = patientService.loadUserByUsername(request.getUsername());
      String token = this.helper.generateToken(userDetails);

        Patients patients =(Patients) userDetails;
        UserDetailsResponse userResponse = userResponseMapper.toUserResponse(patients);

        LoginReponse loginReponse = new LoginReponse();
        loginReponse.setToken(token);
        loginReponse.setUser(userResponse);
        return ResponseEntity.ok().body(new ApiResponse(true, "LoginSucessfully!",loginReponse));
       }catch (UsernameNotFoundException e){
           return ResponseEntity.badRequest().body(new ApiResponseError(false, e.getMessage()));

       }

      // return ResponseEntity.badRequest().body(new ApiResponseError(false,""));
    }














    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        } catch (UsernameNotFoundException e){

        }

    }
}
