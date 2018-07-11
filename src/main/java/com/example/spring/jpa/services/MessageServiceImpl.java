package com.example.spring.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.jpa.models.UserMessage;
import com.example.spring.jpa.repositories.UserMessageRepository;

/**
 *  @author recklessN1nja
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
	public UserMessage findById(Long id) {
		return userMessageRepository.findOne(id);
	}

	@Override
	public void saveMessage(UserMessage userMessage) {
		userMessageRepository.save(userMessage);
	}

	@Override
	public void updateMessage(UserMessage userMessage){
		saveMessage(userMessage);
	}

	@Override
	public void deleteMessageById(Long id){
		userMessageRepository.delete(id);
	}

}
