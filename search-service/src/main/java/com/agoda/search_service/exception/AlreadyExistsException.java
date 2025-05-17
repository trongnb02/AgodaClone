package com.agoda.search_service.exception;

import com.agoda.base_domains.exception.ErrorCode;

public class AlreadyExistsException extends SearchServiceException{
    public AlreadyExistsException(String message) {
        super(message, ErrorCode.SEARCH_SERVICE_ALREADY_EXISTS);
    }
}
