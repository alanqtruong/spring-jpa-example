package com.example.spring.jpa.services;

import com.example.spring.jpa.models.UserMessage;

import java.util.List;

public interface MessageService {

	UserMessage findById(Long id);

	List<UserMessage> findAllMessages();

	void saveMessage(UserMessage userMessage);

	void updateMessage(UserMessage userMessage);

	void deleteMessageById(Long id);

}
