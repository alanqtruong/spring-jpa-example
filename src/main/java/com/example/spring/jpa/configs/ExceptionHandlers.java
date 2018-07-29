package com.example.spring.jpa.configs;

import com.example.spring.jpa.exceptions.UserMessageException;
import com.example.spring.jpa.models.UserResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Handle Exceptions
 * @author alanqtruong
 */
@ControllerAdvice
public class ExceptionHandlers {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<UserResponseMessage> handlMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("Exception {}", ex);
        return new ResponseEntity<>(new UserResponseMessage(ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(". ")), new Date()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<UserResponseMessage> handleConstraintViolationException(ConstraintViolationException ex) {
        logger.error("Exception {}", ex);
        return new ResponseEntity<>(new UserResponseMessage(ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(". ")), new Date()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserMessageException.class)
    public final ResponseEntity<UserResponseMessage> handleUserNotFoundException(UserMessageException ex) {
        logger.error("Exception {}", ex);
        return new ResponseEntity<>(new UserResponseMessage(ex.getMessage(), new Date()), ex.getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<UserResponseMessage> handlHttpMessageNotReadableExceptione(HttpMessageNotReadableException ex) {
        logger.error("Exception {}", ex);
        return new ResponseEntity<>(new UserResponseMessage("Bad JSON Request", new Date()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<UserResponseMessage> handleAllExceptions(Exception ex) {
        logger.error("Exception {}", ex);
        return new ResponseEntity<>(new UserResponseMessage("An error has occurred. Please try again later", new Date()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
