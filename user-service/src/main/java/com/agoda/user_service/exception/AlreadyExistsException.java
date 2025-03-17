package com.agoda.user_service.exception;

import com.agoda.base_domains.exception.ErrorCode;

public class AlreadyExistsException extends UserServiceException{
    public AlreadyExistsException(String message) {
        super(message, ErrorCode.ALREADY_EXISTS);
    }
}
