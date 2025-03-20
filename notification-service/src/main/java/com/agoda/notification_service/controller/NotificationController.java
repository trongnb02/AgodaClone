package com.agoda.notification_service.controller;

import com.agoda.notification_service.dto.request.NotificationRequest;
import com.agoda.notification_service.dto.response.ApiResponse;
import com.agoda.notification_service.model.Notification;
import com.agoda.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getUserNotifications() {
        return ResponseEntity.ok(new ApiResponse("Success!", notificationService.getAllNotifications()));
    }

    @PostMapping("/owner-specific")
    public ResponseEntity<ApiResponse> createOwnerSpecific(@Valid @RequestBody NotificationRequest request) {
        return ResponseEntity.ok(new ApiResponse("Success!", notificationService.createOwnerSpecificNotification(request)));
    }

    @PostMapping("/broadcast")
    public Notification createBroadcast(@Valid @RequestBody NotificationRequest request) {
        return notificationService.createBroadcastNotification(request);
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<ApiResponse> getUserNotifications(@PathVariable String userId) {
        return ResponseEntity.ok(new ApiResponse("Get all notifications of user !", notificationService.getUserNotifications(userId)));
    }

    @GetMapping("/{userId}/unread")
    public ResponseEntity<ApiResponse> getUnreadNotificationsOfUser(@PathVariable String userId) {
        return ResponseEntity.ok(new ApiResponse("Get unread notifications of user !", notificationService.getUnreadNotifications(userId)));
    }

    @GetMapping("/{userId}/{notificationId}/detail")
    public ResponseEntity<ApiResponse> readNotification(@PathVariable String userId, @PathVariable String notificationId) {
        return ResponseEntity.ok(new ApiResponse("Notification detail", notificationService.markNotificationAsRead(notificationId, userId)));
    }
}
