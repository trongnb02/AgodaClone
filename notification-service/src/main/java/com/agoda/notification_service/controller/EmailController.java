package com.agoda.notification_service.controller;

import com.agoda.notification_service.dto.EmailRequest;
import com.agoda.notification_service.response.ApiResponse;
import com.agoda.notification_service.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notification")
public class EmailController {

    private final EmailSenderService emailSenderService;

    @PostMapping("/email")
    public ResponseEntity<ApiResponse> sendEmail(@RequestBody EmailRequest request) {
        try {
            emailSenderService.sendEmail(request);
            return ResponseEntity.ok(new ApiResponse("Send email successfully!", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
