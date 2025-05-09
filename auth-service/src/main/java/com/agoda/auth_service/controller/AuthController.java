package com.agoda.auth_service.controller;

import com.agoda.auth_service.client.UserServiceClient;
import com.agoda.auth_service.dto.request.AuthenticationRequest;
import com.agoda.auth_service.dto.request.ValidateToken;
import com.agoda.auth_service.dto.response.ApiResponse;
import com.agoda.auth_service.dto.response.UserDto;
import com.agoda.auth_service.service.AuthService;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserServiceClient userServiceClient;

    @PostMapping("/testt/{gmail}")
    public ResponseEntity<ApiResponse> test(@PathVariable String gmail) {
        return ResponseEntity.ok(new ApiResponse("Login Success!", userServiceClient.getUserByEmail(gmail).getBody()));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Login Success!", authService.login(request)));
        }
        catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Bad credential !", null));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody ValidateToken request) {
        authService.checkToken(request.getToken());
        return ResponseEntity.ok(new ApiResponse("Successfully validated", null));

    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse> logout(@RequestParam("accessToken") String accessToken) {
        authService.logout(accessToken);
        return ResponseEntity.ok(new ApiResponse("Logout Success!", null));
    }

}
