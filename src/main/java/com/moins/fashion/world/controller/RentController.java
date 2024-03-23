package com.moins.fashion.world.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Rent;
import com.moins.fashion.world.payload.RentDto;
import com.moins.fashion.world.service.RentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/dressrentalsystem/rent")
public class RentController {

	@Autowired
	RentService rentService;

	// Save Rent Details
	@Operation(description = "To Create Rent info", summary = "Rent will be saved in the database")
	@ApiResponses(value = { @ApiResponse(description = "Admin Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping("/{customerEmail}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	public ResponseEntity<ResponseStructure<Rent>> saveRent(@Valid @RequestBody RentDto rentDto, BindingResult result,
			@PathVariable String customerEmail) {
		return this.rentService.saveRent(rentDto, customerEmail, result);
	}

	// Fetch Rent Details by Customer
	@Operation(description = "To fetch Rent details info by customer id", summary = "Rent will be fetched from the database")
	@ApiResponses(value = { @ApiResponse(description = "Rent details fetched", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/customer/{customerId}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	public ResponseEntity<ResponseStructure<List<Rent>>> getRentById(@PathVariable int customerId) {
		return this.rentService.getRentById(customerId);
	}

	// Fetch All Rent Details By Admin
	@Operation(description = "To fetch all rent details info", summary = "Rent details will be fetched from the database")
	@ApiResponses(value = { @ApiResponse(description = "Rent Details fetched", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/admin/{adminId}")
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<ResponseStructure<List<Rent>>> getAllRentDetails(@PathVariable int adminId) {
		return this.rentService.getAllRentDetails(adminId);
	}

	// Cancel Rent
	@Operation(description = "To cancel rent by rent id", summary = "Rent cancellation will be updated in database")
	@ApiResponses(value = { @ApiResponse(description = "Rent Cancelled", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PutMapping("/{rentId}")
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<ResponseStructure<String>> cancelRent(@PathVariable int rentId) {
		return this.rentService.cancelRent(rentId);
	}

	@Operation(description = "To confirm rent by rent id from admin", summary = "Rent confirmation will be updated in database")
	@ApiResponses(value = { @ApiResponse(description = "Rent Confirmed", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	// Confirm Rent By Admin
	@PutMapping("/confirm/{rentId}")
	public ResponseEntity<ResponseStructure<String>> confirmRent(@PathVariable int rentId) {
		return this.rentService.confirmRent(rentId);
	}
}
