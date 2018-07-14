package com.example.spring.jpa.controllers;

import com.example.spring.jpa.models.UserMessageError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *  @author alanqtruong
 */
@RestController
@RequestMapping("/")
public class BaseController {

	/**
	 * Catch all invalid request
	 * @return generic page not found message
	 */
	@RequestMapping("**")
	public ResponseEntity<UserMessageError> catchAll() {
		return new ResponseEntity<>(new UserMessageError("Page not found", new Date()), HttpStatus.NOT_FOUND);
	}

}