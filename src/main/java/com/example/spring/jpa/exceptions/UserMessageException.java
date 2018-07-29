package com.example.spring.jpa.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author alanqtruong
 */
public class UserMessageException extends RuntimeException {

    private final HttpStatus httpStatus;

    public UserMessageException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
