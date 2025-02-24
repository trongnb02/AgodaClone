package com.agoda.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ValidateToken {
    @NotBlank(message = "Token is required")
    private String token;
}
