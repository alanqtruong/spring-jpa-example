package com.example.spring.jpa.configs;

import com.example.spring.jpa.exceptions.UserMessageException;
import com.example.spring.jpa.models.UserMessageError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * Handle Exceptions
 * @author alanqtruong
 */
@ControllerAdvice
public class ExceptionHandlers {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(UserMessageException.class)
    public final ResponseEntity<UserMessageError> handleUserNotFoundException(UserMessageException ex) {
        logger.error("Exception {}", ex);
        UserMessageError errorDetails = new UserMessageError(ex.getMessage(), new Date());
        return new ResponseEntity<>(errorDetails, ex.getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<UserMessageError> handlHttpMessageNotReadableExceptione(HttpMessageNotReadableException ex) {
        logger.error("Exception {}", ex);
        UserMessageError errorDetails = new UserMessageError("Bad JSON Request", new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<UserMessageError> handleAllExceptions(Exception ex) {
        logger.error("Exception {}", ex);
        UserMessageError errorDetails = new UserMessageError("An error has occurred. Please try again later", new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
