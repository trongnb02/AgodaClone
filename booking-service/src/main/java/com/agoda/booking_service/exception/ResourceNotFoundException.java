package com.agoda.booking_service.exception;

import com.agoda.base_domains.exception.ErrorCode;

public class ResourceNotFoundException extends BookingServiceException {
    public ResourceNotFoundException(String message) {
        super(message, ErrorCode.BOOKING_SERVICE_RESOURCE_NOT_FOUND);
    }
}
