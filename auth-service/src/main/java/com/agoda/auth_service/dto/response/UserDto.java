package com.agoda.auth_service.dto.response;

import com.agoda.auth_service.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String email;
    private String username;
    private Role role;
    private String password;
}
