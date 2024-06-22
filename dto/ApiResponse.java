package com.health.pramod.HealthCare.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;


}
