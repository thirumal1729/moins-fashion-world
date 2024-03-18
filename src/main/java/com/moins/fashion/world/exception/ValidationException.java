package com.moins.fashion.world.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ValidationException extends RuntimeException {
	private String message;

	@Override
	public String getMessage() {
		return this.message;
	}
}
