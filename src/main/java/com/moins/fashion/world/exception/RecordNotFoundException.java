package com.moins.fashion.world.exception;

public class RecordNotFoundException extends RuntimeException{

	private String msg ="Unable to find such record";

	@Override
	public String getMessage() {
		
		 return this.msg;
	}
	
	
}
