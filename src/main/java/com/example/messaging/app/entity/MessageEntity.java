package com.example.messaging.app.entity;

public class MessageEntity {

	private String number;
	private String message;
	
	public MessageEntity(String number, String message) {
		super();
		this.number = number;
		this.message = message;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
		
		
		
}
