package com.moins.fashion.world.service;

import org.springframework.http.ResponseEntity;

import com.moins.fashion.world.dto.CustomerDto;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;

import jakarta.validation.Valid;

public interface CustomerService {

	ResponseEntity<ResponseStructure<Customer>> createCustomer(@Valid CustomerDto customerDto);

	ResponseEntity<ResponseStructure<Customer>> updateCustomer(@Valid String email, @Valid String password,
			@Valid Customer customer);

	ResponseEntity<ResponseStructure<Customer>> changePassword(@Valid String email, @Valid String old_password,
			@Valid String new_password);

}
