package com.agoda.auth_service.controller;

import com.agoda.auth_service.dto.request.AuthenticationRequest;
import com.agoda.auth_service.dto.request.ValidateToken;
import com.agoda.auth_service.dto.response.ApiResponse;
import com.agoda.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(new ApiResponse("Login Success!", authService.login(request)));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody ValidateToken request) {
        if (!authService.checkToken(request.getToken())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Token is not valid", null));
        }
        return ResponseEntity.ok(new ApiResponse("Successfully validated", null));

    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse> logout(@RequestParam("accessToken") String accessToken) {
        authService.logout(accessToken);
        return ResponseEntity.ok(new ApiResponse("Logout Success!", null));
    }

}
