package com.moins.fashion.world.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rent")
public class RentController {

	@Autowired
	RentService rentService;

	// Save Rent Details
	@PostMapping("/{customerEmail}")
	public ResponseEntity<ResponseStructure<Rent>> saveRent(@Valid @RequestBody RentDto rentDto,
			@PathVariable String customerEmail) {
		return this.rentService.saveRent(rentDto, customerEmail);
	}

	// Fetch Rent Details by Customer
	@GetMapping("/{customerId}")
	public ResponseEntity<ResponseStructure<List<Rent>>> getRentById(@PathVariable int customerId) {
		return this.rentService.getRentById(customerId);
	}

	// Fetch All Rent Details By Admin
	@GetMapping("/all/{adminId}")
	public ResponseEntity<ResponseStructure<List<Rent>>> getAllRentDetails(@PathVariable int adminId) {
		return this.rentService.getAllRentDetails(adminId);
	}

	// Cancel Rent
	@PutMapping("/{rentId}")
	public ResponseEntity<ResponseStructure<String>> cancelRent(@PathVariable int rentId) {
		return this.rentService.cancelRent(rentId);
	}

	// Confirm Rent By Admin
	@PutMapping("/confirm/{rentId}")
	public ResponseEntity<ResponseStructure<String>> confirmRent(@PathVariable int rentId) {
		return this.rentService.confirmRent(rentId);
	}
}
