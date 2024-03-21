package com.moins.fashion.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moins.fashion.world.dto.CustomerDto;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.service.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customer")
	ResponseEntity<ResponseStructure<Customer>> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
		return customerService.createCustomer(customerDto);
	}

//	@GetMapping("/customer")
//	ResponseEntity<ResponseStructure<Customer>>login(@Valid @RequestParam String email,@Valid @RequestParam String password){
//		return customerService.loginCustomer(email, password);
//	}

	@PutMapping("/customer/{id}")
	ResponseEntity<ResponseStructure<Customer>> updateCustomer(@PathVariable int id, @Valid @RequestBody CustomerDto customer) {
		return customerService.updateCustomer(id, customer);
	}

	@PutMapping("/customer/changepassword/{id}")
	ResponseEntity<ResponseStructure<Customer>> changePassword(@PathVariable int id , @Valid @RequestParam String new_password) {
		return customerService.changePassword( id, new_password);
	}
 
}
