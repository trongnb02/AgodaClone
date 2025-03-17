package com.agoda.user_service.exception;

import com.agoda.base_domains.exception.ErrorCode;

public class EmailNotFoundException extends UserServiceException{
    public EmailNotFoundException(String message) {
        super(message, ErrorCode.EMAIL_NOT_FOUND);
    }
}
