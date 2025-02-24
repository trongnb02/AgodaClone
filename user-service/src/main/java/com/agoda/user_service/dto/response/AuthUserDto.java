package com.agoda.user_service.dto.response;

import com.agoda.user_service.model.enums.Role;
import lombok.Data;

@Data
public class AuthUserDto {
    private String id;
    private String username;
    private String password;
    private Role role;
}
