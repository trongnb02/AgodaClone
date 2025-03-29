package com.agoda.booking_service.exception;

import com.agoda.base_domains.exception.ErrorCode;

public class AlreadyExistsException extends BookingServiceException{
    public AlreadyExistsException(String message) {
        super(message, ErrorCode.BOOKING_SERVICE_ALREADY_EXISTS);
    }
}
