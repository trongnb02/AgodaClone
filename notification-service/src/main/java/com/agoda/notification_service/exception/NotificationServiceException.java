package com.agoda.notification_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationServiceException extends RuntimeException{
    private String message;
    private String code;
    public NotificationServiceException(String message){
        super(message);
    }
}
