package com.example.messaging.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import com.example.messaging.app.entity.MessageEntity;

@Component
public class MessageSender {

	@Autowired
	JmsTemplate jmsTemplate;

	@Retryable( value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
	public void sendMessage(final MessageEntity messageEntity) {
		Map<String,String> messageMap = new HashMap<String, String>();
		messageMap.put(messageEntity.getNumber(), messageEntity.getMessage());
		jmsTemplate.convertAndSend(messageMap);
	}

}
