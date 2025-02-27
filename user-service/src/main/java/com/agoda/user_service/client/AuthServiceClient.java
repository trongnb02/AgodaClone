package com.agoda.user_service.client;


import com.agoda.user_service.dto.request.ValidateToken;
import com.agoda.user_service.dto.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", path = "/api/v1/auth")
public interface AuthServiceClient {
    @PostMapping("/validate")
    ResponseEntity<ApiResponse> validateToken(@Valid @RequestBody ValidateToken request);
}
