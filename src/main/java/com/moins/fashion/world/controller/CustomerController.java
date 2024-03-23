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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/dressrentalsystem/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Operation(description = "To update Customer info", summary = "Customer will be updated in the database")
	@ApiResponses(value = { @ApiResponse(description = "Customer Update", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PutMapping("/{id}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	ResponseEntity<ResponseStructure<Customer>> updateCustomer(@PathVariable int id,
			@Valid @RequestBody CustomerDto customer) {
		return customerService.updateCustomer(id, customer);
	}

	@Operation(description = "To update Customer password", summary = "Customer password is updated")
	@ApiResponses(value = { @ApiResponse(description = "Customer password updated", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PutMapping("/changepassword/{id}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	ResponseEntity<ResponseStructure<Customer>> changePassword(@PathVariable int id,
			@Valid @RequestParam String new_password) {
		return customerService.changePassword(id, new_password);
	}

}
