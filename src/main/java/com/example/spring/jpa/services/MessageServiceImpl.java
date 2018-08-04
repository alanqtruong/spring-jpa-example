package com.example.spring.jpa.services;

import java.util.List;

import com.example.spring.jpa.exceptions.UserMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.jpa.models.UserMessage;
import com.example.spring.jpa.repositories.UserMessageRepository;

/**
 *  @author alanqtruong
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService{

	@Autowired
	private UserMessageRepository userMessageRepository;

	@Override
	public List<UserMessage> findAllMessages() {
		return userMessageRepository.findAll();
	}

	@Override
	public UserMessage findById(long id) {
		UserMessage message =  userMessageRepository.findOne(id);
		if (message == null) throw new UserMessageException("message with id " + id + " not found", HttpStatus.NOT_FOUND);
		return message;
	}

	@Override
	public void saveMessage(UserMessage userMessage) {
		if (isPresent(userMessage.getId())) throw new UserMessageException("Message with id " + userMessage.getId() + " already exist ", HttpStatus.BAD_REQUEST);
		userMessageRepository.save(userMessage);
	}

	@Override
	public void updateMessage(long id, UserMessage userMessage){
		userMessage.setId(id);
		UserMessage currentMessage = findById(id);
		if (currentMessage == null) throw new UserMessageException("Unable to update message with id " + id + " not found.", HttpStatus.NOT_FOUND);
		currentMessage.setMessage(userMessage.getMessage());
		userMessageRepository.save(currentMessage);
	}

	@Override
	public void deleteMessageById(long id){
		if (!isPresent(id)) throw new UserMessageException("Unable to delete message with id " + id + " not found.", HttpStatus.NOT_FOUND);
		userMessageRepository.delete(id);
	}

	@Override
	public boolean isPresent(long id) {
		return userMessageRepository.exists(id);
	}

}
