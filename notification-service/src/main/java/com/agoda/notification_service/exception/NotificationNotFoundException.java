package com.agoda.notification_service.exception;

import com.agoda.base_domains.exception.ErrorCode;

public class NotificationNotFoundException extends NotificationServiceException {
    public NotificationNotFoundException(String message) {
        super(message, ErrorCode.NOTIFICATION_NOT_FOUND);
    }
}
