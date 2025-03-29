package com.agoda.booking_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingServiceException extends RuntimeException{
    private String message;
    private String code;
    public BookingServiceException(String message){
        super(message);
    }
}
