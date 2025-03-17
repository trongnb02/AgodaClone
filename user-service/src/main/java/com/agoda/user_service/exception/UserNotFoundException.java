package com.agoda.user_service.exception;

import com.agoda.base_domains.exception.ErrorCode;

public class UserNotFoundException extends UserServiceException{
    public UserNotFoundException(String message) {
        super(message, ErrorCode.USER_NOT_FOUND);
    }
}
