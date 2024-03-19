package com.moins.fashion.world.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NoFileException extends RuntimeException {

	private String message = "No file";
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
