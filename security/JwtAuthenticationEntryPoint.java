package com.health.pramod.HealthCare.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.pramod.HealthCare.dto.ApiResponse;
import com.health.pramod.HealthCare.dto.ApiResponseError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        ApiResponseError apiResponse = new ApiResponseError(false, "Access Denied " +authException.getMessage() );
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);

        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
    }
}
