package com.example.spring.jpa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author recklessN1nja
 */
@RestController
@RequestMapping("/")
public class BaseController {

	/**
	 * Catch all invalid request
	 * @return generic error reponse message
	 */
	@RequestMapping("**")
	public ResponseEntity<String> catchAll() {
		return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
	}

}