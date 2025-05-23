package com.agoda.notification_service.model;

import com.agoda.notification_service.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @MongoId
    @Field("id")
    private String id = UUID.randomUUID().toString();
    private String title;
    private String content;
    private NotificationType type;
    private String email;
    private LocalDateTime createdAt;
    private boolean isRead = false;

    public Notification(String title, String content, NotificationType type, String email, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.email = email;
        this.createdAt = createdAt;
    }
}
