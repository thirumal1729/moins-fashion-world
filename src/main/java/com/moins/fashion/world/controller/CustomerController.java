package com.moins.fashion.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.payload.JwtRequest;
import com.moins.fashion.world.payload.JwtResponse;
import com.moins.fashion.world.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PutMapping
	ResponseEntity<ResponseStructure<Customer>> updateCustomer(@Valid @RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}

	@PutMapping("/changepassword")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	ResponseEntity<ResponseStructure<Customer>> changePassword(@Valid @RequestParam String email,
			@Valid @RequestParam String old_password, @Valid String new_password) {
		return customerService.changePassword(email, old_password, new_password);
	}

}
