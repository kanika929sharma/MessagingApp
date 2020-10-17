package com.example.messaging.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.messaging.app.entity.MessageEntity;
import com.example.messaging.app.exception.MessagingException;
import com.example.messaging.app.service.MessageSender;

@RestController
public class MessagingController {
	
	@Autowired
	MessageSender messageSender;
	
	@PostMapping("/sendMessage")
	public String greeting(@RequestBody MessageEntity messageEntity) {		
		messageSender.sendMessage(messageEntity);
		return "Message Sent Successfully";
    }

}
