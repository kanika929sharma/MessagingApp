package com.example.messaging.app.exception;

public class MessagingException extends RuntimeException{
	private static final String DEFAULT_MESSAGE = "Some error occurred. Please try again";
	
	public MessagingException() {
		super(DEFAULT_MESSAGE);
	}
	
	public MessagingException(String message) {
		super(message);
	}
	
	public MessagingException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
