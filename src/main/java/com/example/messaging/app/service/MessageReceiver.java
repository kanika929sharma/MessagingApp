package com.example.messaging.app.service;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.example.messaging.app.entity.MessageEntity;

@Component
public class MessageReceiver {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	MessageConverter messageConverter;

	public MessageEntity receiveMessage() throws MessageConversionException, JMSException	{
			MessageEntity messageEntity = new MessageEntity(null, null);
			Message message = jmsTemplate.receive();
			Map<String,String> messageMap = (Map) messageConverter.fromMessage(message);
			messageEntity.setNumber((String)messageMap.keySet().toArray()[0]);
			messageEntity.setMessage((String)messageMap.values().toArray()[0]);			
			return messageEntity;
	}
}
