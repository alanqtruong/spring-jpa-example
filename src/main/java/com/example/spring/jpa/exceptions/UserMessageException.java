package com.example.spring.jpa.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.util.stream.Collectors;

/**
 * @author alanqtruong
 */
public class UserMessageException extends RuntimeException{

    private final HttpStatus httpStatus;

    public UserMessageException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public UserMessageException(Errors errors, HttpStatus httpStatus){
        super(errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(". ")));
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
