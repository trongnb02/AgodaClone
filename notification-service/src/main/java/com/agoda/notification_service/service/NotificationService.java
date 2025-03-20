package com.agoda.notification_service.service;

import com.agoda.notification_service.client.UserServiceClient;
import com.agoda.notification_service.dto.request.NotificationRequest;
import com.agoda.notification_service.exception.NotificationNotFoundException;
import com.agoda.notification_service.model.Notification;
import com.agoda.notification_service.model.enums.NotificationType;
import com.agoda.notification_service.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserServiceClient userServiceClient;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification createOwnerSpecificNotification(NotificationRequest request) {
        userServiceClient.getUserByEmail(request.getEmail());
        Notification notification = new Notification(request.getTitle(), request.getContent(), NotificationType.OWNER_SPECIFIC, request.getEmail(), LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public Notification createBroadcastNotification(NotificationRequest request) {
        Notification notification = new Notification(request.getTitle(), request.getContent(), NotificationType.BROADCAST, null, LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(String email) {
        userServiceClient.getUserByEmail(email);
        return notificationRepository.findByEmail(email);
    }

    public List<Notification> getUnreadNotifications(String email) {
        userServiceClient.getUserByEmail(email);
        return notificationRepository.findUnreadByEmail(email);
    }

    public Notification markNotificationAsRead(String notificationId, String userId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(
                () -> new NotificationNotFoundException("Notification not found")
        );
        notification.setRead(true);
        return notificationRepository.save(notification);
    }
}

