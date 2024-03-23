package com.moins.fashion.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moins.fashion.world.dto.CustomerDto;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.payload.JwtRequest;
import com.moins.fashion.world.payload.JwtResponse;
import com.moins.fashion.world.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/dressrentalsystem/customerManager")
public class CustomerManageController {

	@Autowired
	private CustomerService customerService;

	@Operation(description = "To Create Customer info", summary = "Customer will be saved in the database")
	@ApiResponses(value = { @ApiResponse(description = "Customer Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping
	ResponseEntity<ResponseStructure<Customer>> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
		return customerService.createCustomer(customerDto);
	}

	// Customer Login
	@Operation(description = "To Login as customer", summary = "Customer will login")
	@ApiResponses(value = { @ApiResponse(description = "Customer logged in", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping("/login")
	ResponseEntity<ResponseStructure<JwtResponse>> login(@RequestBody JwtRequest request) {
		return customerService.customerLogin(request);
	}

}
