package com.agoda.hotel_service.exception;

import com.agoda.base_domains.exception.ErrorCode;

public class ResourceNotFoundException extends HotelServiceException {
    public ResourceNotFoundException(String message) {
        super(message, ErrorCode.HOTEL_SERVICE_RESOURCE_NOT_FOUND);
    }
}
