package com.example.spring.jpa.controllers;

import java.util.List;

import com.example.spring.jpa.models.UserMessage;
import com.example.spring.jpa.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *  @author recklessN1nja
 */
@RestController
@RequestMapping("/api")
public class RestAPIController {

	public static final Logger logger = LoggerFactory.getLogger(RestAPIController.class);

	@Autowired
	private MessageService messageService;

	//returns all the messages
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ResponseEntity<List<UserMessage>> listAllUsers() {
		logger.info("fetching all messages");
		List<UserMessage> messages = messageService.findAllMessages();
		if (messages.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<UserMessage>>(messages, HttpStatus.OK);
		}
	}

	//returns the message for a given id
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMessage(@PathVariable("id") long id) {
		logger.info("fetching message with id {}", id);
		UserMessage meesage = messageService.findById(id);
		if (meesage == null) {
			return new ResponseEntity<String>("message with id " + id
					+ " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserMessage>(meesage, HttpStatus.OK);
	}

	//create message
	@RequestMapping(value = "/message/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserMessage userMessage, UriComponentsBuilder ucBuilder) {
		logger.info("Creating message {}", userMessage);

		messageService.saveMessage(userMessage);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/message/{id}").buildAndExpand(userMessage.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	//update existing message
	@RequestMapping(value = "/message/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMessage(@PathVariable("id") long id, @RequestBody UserMessage userMessage) {
		logger.info("Updating message with id {}", id);

		UserMessage currentMessage = messageService.findById(id);

		if (currentMessage == null) {
			logger.error("Unable to update. Message with id {} not found.", id);
			return new ResponseEntity<String>("Unable to update message with id " + id + " not found.",
					HttpStatus.NOT_FOUND);
		}

		currentMessage.setUser_message(userMessage.getUser_message());

		messageService.updateMessage(currentMessage);
		return new ResponseEntity<UserMessage>(currentMessage, HttpStatus.OK);
	}

	// delete message
	@RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMessage(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting message with id {}", id);

		UserMessage userMessage = messageService.findById(id);
		if (userMessage == null) {
			logger.error("Unable to delete message with id {} not found.", id);
			return new ResponseEntity<String>("Unable to delete message with id " + id + " not found.",
					HttpStatus.NOT_FOUND);
		}
		messageService.deleteMessageById(id);
		return new ResponseEntity<UserMessage>(HttpStatus.NO_CONTENT);
	}
}
