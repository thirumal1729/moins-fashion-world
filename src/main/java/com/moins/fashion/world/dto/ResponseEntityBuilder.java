package com.moins.fashion.world.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder<T> {

	private ResponseStructure<Object> response;

	public ResponseEntityBuilder<T> responseBack(ResponseStructure<Object> responseStructure) {
		this.response = responseStructure;
		return this;
	}

	public ResponseEntity<ResponseStructure<Object>> build() {
		return new ResponseEntity<ResponseStructure<Object>>(response, HttpStatus.CREATED);
	}
}
