package com.example.spring.jpa.controllers;

import com.example.spring.jpa.models.UserMessage;
import com.example.spring.jpa.models.UserResponseMessage;
import com.example.spring.jpa.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 *  @author alanqtruong
 */
@RestController
@Validated
@RequestMapping("/api")
public class RestAPIController {

	private static final Logger logger = LoggerFactory.getLogger(RestAPIController.class);

	@Autowired
	private MessageService messageService;

	//returns all the messages
	@GetMapping(value = "/message")
	public ResponseEntity<List<UserMessage>> listAllMessages() {
		logger.info("fetching all messages");
		List<UserMessage> messages = messageService.findAllMessages();
		return messages.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(messages, HttpStatus.OK);
	}

	//returns the message for a given id
	@GetMapping(value = "/message/{id}")
	public ResponseEntity<UserMessage> getMessage(@PathVariable("id") long id) {
		logger.info("Fetching message with id {}", id);
		return new ResponseEntity<>(messageService.findById(id), HttpStatus.OK);
	}

	//create message
	@PostMapping(value = "/message")
	public ResponseEntity<UserResponseMessage> createMessage(@Valid @RequestBody UserMessage userMessage) {
		logger.info("Creating message {}", userMessage);
		messageService.saveMessage(userMessage);
		return new ResponseEntity<>(new UserResponseMessage("Successfully created message", new Date()), HttpStatus.CREATED);
	}

	//update existing message
	@PutMapping(value = "/message/{id}")
	public ResponseEntity<UserResponseMessage> updateMessage(@PathVariable("id") long id, @Valid @RequestBody UserMessage userMessage) {
		logger.info("Updating message with id {}", id);
		messageService.updateMessage(id, userMessage);
		return new ResponseEntity<>(new UserResponseMessage("Successfully updated message", new Date()), HttpStatus.OK);
	}

	// delete message
	@DeleteMapping(value = "/message/{id}")
	public ResponseEntity<?> deleteMessage(@PathVariable("id") long id) {
		logger.info("Deleting message with id {}", id);
		messageService.deleteMessageById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
