package com.moins.fashion.world.exception;

public class MailFailedToSendException extends Exception {
	String message="Mail Delivery Failed";

	public MailFailedToSendException() {
		
	}

	public MailFailedToSendException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
