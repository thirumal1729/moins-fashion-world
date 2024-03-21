package com.moins.fashion.world.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RentDetailsNotFoundException extends Exception {

	String message = "Rent details Not found";
	
	@Override
	public String getMessage() {
		return message;
	}
}
