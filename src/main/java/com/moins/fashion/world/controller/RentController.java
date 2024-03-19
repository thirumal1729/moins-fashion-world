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

	@PostMapping
	public ResponseEntity<ResponseStructure<Rent>> saveRent(@Valid @RequestBody RentDto rentDto) {
		return this.rentService.saveRent(rentDto);
	}

	@GetMapping("/{rentId}/{customerId}")
	public ResponseEntity<ResponseStructure<Rent>> getRentById(@PathVariable int rentId, @PathVariable int customerId) {
		return this.rentService.getRentById(rentId, customerId);
	}

	@GetMapping("/{adminId}")
	public ResponseEntity<ResponseStructure<List<Rent>>> getAllRentDetails(@PathVariable int adminId) {
		return this.rentService.getAllRentDetails(adminId);
	}

	@PutMapping("/{rentId}")
	public ResponseEntity<ResponseStructure<String>> cancelRent(@PathVariable int rentId) {
		return this.rentService.cancelRent(rentId);
	}
	
	@PutMapping("/confirm/{rentId}")
	public ResponseEntity<ResponseStructure<String>> confirmRent(@PathVariable int rentId) {
		return this.rentService.confirmRent(rentId);
	}
}
