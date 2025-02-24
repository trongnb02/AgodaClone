package com.agoda.user_service.dto.request;

import com.agoda.user_service.model.UserDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotBlank(message = "Email is required")
    private String email;
    private String username;
    private String password;
    private UserDetails userDetails;
}
