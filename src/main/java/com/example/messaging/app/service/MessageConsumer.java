package com.example.messaging.app.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.messaging.app.entity.MessageEntity;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;


@Component
public class MessageConsumer {
	
	@Autowired
	MessageReceiver messageReceiver;
	
	@Value("${spring.twilio.account.sid}")
	public String accountId;
	
	@Value("${spring.twilio.account.authtoken}")
    public String authToken;
	
	@Value("${spring.twilio.account.twilionumber}")
    public String fromNumber;
	
	@Scheduled(fixedDelay=1000)
	public void receiveMessage() {
		try {
			MessageEntity messageEntity = messageReceiver.receiveMessage();
			sendMessage(messageEntity.getMessage(), messageEntity.getNumber());
			System.out.println("message " + messageEntity.getMessage());
		} catch (Exception e) {
			throw new MessagingException("Error occurred while receiving message from queue " + e.getMessage());
		}
	}

	public void sendMessage(String messageBody, String number) {
		try {
	        TwilioRestClient client = new TwilioRestClient(accountId, authToken);	       
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("Body", messageBody));
	        params.add(new BasicNameValuePair("To", number)); 
	        params.add(new BasicNameValuePair("From", fromNumber));

	        MessageFactory messageFactory = client.getAccount().getMessageFactory();
	        Message message = messageFactory.create(params);
	        System.out.println(message.getSid());
	    } 
	    catch (TwilioRestException e) {
	        throw new MessagingException("Error occurred while sending messaging to phone number: " 
	        		+ number + " " + e.getMessage());
	    }
	}
}
