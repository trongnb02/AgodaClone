package com.agoda.auth_service.exception;

import com.agoda.base_domains.exception.ErrorCode;
import com.agoda.base_domains.exception.ErrorResponse;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserServiceException.class)
    protected ResponseEntity<ErrorResponse> handleUserServiceException(UserServiceException ex) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(AuthServiceException.class)
    protected ResponseEntity<ErrorResponse> handleAuthServiceException(AuthServiceException ex) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(JwtException.class)
    protected ResponseEntity<ErrorResponse> handleJwtException(JwtException ex) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .code(ErrorCode.INVALID_TOKEN)
                        .message("Token is not valid")
                        .build());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .code(ErrorCode.INTERNAL_SERVER_ERROR_AUTH_SERVICE)
                        .message("Exception in auth Service: " + ex)
                        .build());
    }

}
