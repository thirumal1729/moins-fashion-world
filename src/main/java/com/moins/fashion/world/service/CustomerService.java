package com.moins.fashion.world.service;

import org.springframework.http.ResponseEntity;

import com.moins.fashion.world.dto.CustomerDto;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.payload.JwtRequest;
import com.moins.fashion.world.payload.JwtResponse;

public interface CustomerService {

	ResponseEntity<ResponseStructure<Customer>> createCustomer(CustomerDto customerDto);

	ResponseEntity<ResponseStructure<Customer>> updateCustomer(int id, CustomerDto customerdto);

	ResponseEntity<ResponseStructure<Customer>> changePassword(int id, String new_password);

	ResponseEntity<ResponseStructure<Customer>> findById(int id);
	
	ResponseEntity<ResponseStructure<JwtResponse>> customerLogin(JwtRequest request);
}
