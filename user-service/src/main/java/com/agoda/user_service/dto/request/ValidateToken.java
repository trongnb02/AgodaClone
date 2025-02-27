package com.agoda.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateToken {
    @NotBlank(message = "Token is required")
    private String token;
}
