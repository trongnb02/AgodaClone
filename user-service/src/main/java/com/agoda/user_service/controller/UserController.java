package com.agoda.user_service.controller;

import com.agoda.user_service.dto.request.RegisterRequest;
import com.agoda.user_service.dto.request.UpdateUserRequest;
import com.agoda.user_service.dto.response.ApiResponse;
import com.agoda.user_service.dto.response.UserDto;
import com.agoda.user_service.exception.AlreadyExistsException;
import com.agoda.user_service.exception.EmailNotFoundException;
import com.agoda.user_service.exception.UserNotFoundException;
import com.agoda.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/logintest")
    public ResponseEntity<ApiResponse> test() {
        try {
            return ResponseEntity.ok(new ApiResponse("Test", null));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserById(id), UserDto.class));
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<ApiResponse> getUserByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(new ApiResponse("Success!", modelMapper.map(userService.getUserByEmail(email), UserDto.class)));
        } catch (EmailNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Success!",modelMapper.map(userService.register(request), UserDto.class)));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUser(@Valid @RequestBody UpdateUserRequest request){
        try {
            return ResponseEntity.ok(new ApiResponse("Success!",modelMapper.map(userService.updateUserByEmail(request), UserDto.class)));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
