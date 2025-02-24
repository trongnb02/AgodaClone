package com.agoda.auth_service.client;

import com.agoda.auth_service.dto.response.ApiResponse;
import com.agoda.auth_service.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", path = "/api/v1/user")
public interface UserServiceClient {

    @GetMapping("/getUserByEmail/{email}")
    ResponseEntity<ApiResponse> getUserByEmail(@PathVariable String email);
}
