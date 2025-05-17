package com.agoda.search_service.exception;

import com.agoda.base_domains.exception.ErrorCode;

public class ResourceNotFoundException extends SearchServiceException {
    public ResourceNotFoundException(String message) {
        super(message, ErrorCode.SEARCH_SERVICE_RESOURCE_NOT_FOUND);
    }
}
