package com.agoda.search_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchServiceException extends RuntimeException{
    private String message;
    private String code;
    public SearchServiceException(String message) {
        super(message);
    }
}
