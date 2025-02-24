package com.agoda.user_service.dto.response;

import com.agoda.user_service.model.UserDetails;
import com.agoda.user_service.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private UserDetails userDetails;
}
