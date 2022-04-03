package com.springframe.springframe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomErrorType extends RuntimeException{
    public CustomErrorType(String message){
        super(message);
    }
}
