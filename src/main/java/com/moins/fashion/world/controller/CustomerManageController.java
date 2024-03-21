package com.moins.fashion.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.payload.JwtRequest;
import com.moins.fashion.world.payload.JwtResponse;
import com.moins.fashion.world.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customerManager")
public class CustomerManageController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	ResponseEntity<ResponseStructure<Customer>> createCustomer(@Valid @RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}

	@PostMapping("/login")
	ResponseEntity<ResponseStructure<JwtResponse>>login(@RequestBody JwtRequest request){
		return customerService.customerLogin(request);
	}
	
}
