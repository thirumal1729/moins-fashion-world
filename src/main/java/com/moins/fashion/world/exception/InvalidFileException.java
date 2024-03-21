package com.moins.fashion.world.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InvalidFileException extends RuntimeException {

	private String message = "invalid file format";
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
