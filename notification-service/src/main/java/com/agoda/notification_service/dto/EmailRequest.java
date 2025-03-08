package com.agoda.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    private String receiverEmail;
    private String subject;
    private String body;
}
