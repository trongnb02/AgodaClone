package com.agoda.hotel_service.exception;

import com.agoda.base_domains.exception.ErrorCode;

public class AlreadyExistsException extends HotelServiceException{
    public AlreadyExistsException(String message) {
        super(message, ErrorCode.HOTEL_SERVICE_ALREADY_EXISTS);
    }
}
