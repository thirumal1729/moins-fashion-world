package com.moins.fashion.world.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DressesNotAvailableException extends RuntimeException {
	private String message = "Dresses List are Empty";

	@Override
	public String getMessage() {
		return this.message;
	}
}
