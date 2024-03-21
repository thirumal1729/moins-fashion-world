package com.moins.fashion.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moins.fashion.world.dto.CustomerDto;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PutMapping("/customer/{id}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	ResponseEntity<ResponseStructure<Customer>> updateCustomer(@PathVariable int id, @Valid @RequestBody CustomerDto customer) {
		return customerService.updateCustomer(id, customer);
	}

	@PutMapping("/customer/changepassword/{id}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	ResponseEntity<ResponseStructure<Customer>> changePassword(@PathVariable int id , @Valid @RequestParam String new_password) {
		return customerService.changePassword( id, new_password);
	}
 
}
