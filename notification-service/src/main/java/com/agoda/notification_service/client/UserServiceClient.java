package com.agoda.notification_service.client;

import com.agoda.notification_service.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", path = "/api/v1/user", configuration = CustomFeignClientConfiguration.class)
public interface UserServiceClient {

    @GetMapping("/getUserByEmail/{email}")
    ResponseEntity<ApiResponse> getUserByEmail(@PathVariable String email);
}