package com.moins.fashion.world.service;

import org.springframework.http.ResponseEntity;

import com.moins.fashion.world.dto.CustomerDto;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;

import jakarta.validation.Valid;

public interface CustomerService {

	ResponseEntity<ResponseStructure<Customer>> createCustomer(@Valid CustomerDto customerDto);

	ResponseEntity<ResponseStructure<Customer>> updateCustomer(int id,
			@Valid CustomerDto customerdto);

	ResponseEntity<ResponseStructure<Customer>> changePassword(int id,
			@Valid String new_password);

}
