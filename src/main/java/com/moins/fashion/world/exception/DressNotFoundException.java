package com.moins.fashion.world.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DressNotFoundException extends RuntimeException {
	private String message = "Dress Not Found";

	@Override
	public String getMessage() {
		return this.message;
	}
}
