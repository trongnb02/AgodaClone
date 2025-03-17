package com.agoda.user_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceException extends RuntimeException{
    private String message;
    private String code;
    public UserServiceException(String message){
        super(message);
    }
}
