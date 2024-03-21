package com.moins.fashion.world.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.moins.fashion.world.dto.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseStructure<String>> catchValidationException(ValidationException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(exception.getMessage());
		responseStructure.setData("Please Enter the valid details");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}

	// DressNotFoundException
	@ExceptionHandler(DressNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> catchDressNotFoundException(DressNotFoundException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(exception.getMessage());
		responseStructure.setData("No Dresses are Available");

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

	// DressesNotAvailableException
	@ExceptionHandler(DressesNotAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> catchDressesNotAvailableException(
			DressesNotAvailableException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage(exception.getMessage());
		responseStructure.setData("No Dresses are Available");

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	}


	// InvalidFileException
	@ExceptionHandler(InvalidFileException.class)
	public ResponseEntity<ResponseStructure<String>> catchInvalidFileException(InvalidFileException exception) {
		
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(exception.getMessage());
		responseStructure.setData("Bad Request");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}
	
	// RentDetailNotAvailableException
	@ExceptionHandler(RentDetailsNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> catchRentDetailsNotAvailableException(
			RentDetailsNotFoundException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage(exception.getMessage());
		responseStructure.setData("No Rent details are Available!!");

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	}

	// SqlIntegrityConstaraintViolationException
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> catchSqlIntegrityConstraintViolationException(
			RentDetailsNotFoundException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(exception.getMessage());
		responseStructure.setData("Bad Request");
		responseStructure.setData("Data might be duplicated!!");

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}


	// NoFileException
	@ExceptionHandler(NoFileException.class)
	public ResponseEntity<ResponseStructure<String>> catchNoFileException(NoFileException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(exception.getMessage());
		responseStructure.setData("Bad Request");

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MailFailedToSendException.class)
	public ResponseEntity<ResponseStructure<String>> catchMailFailedException(MailFailedToSendException exception) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("BAD REQUEST");
		structure.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}
}
