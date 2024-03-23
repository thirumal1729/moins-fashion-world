package com.moins.fashion.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Admin;
import com.moins.fashion.world.payload.AdminDto;
import com.moins.fashion.world.payload.JwtRequest;
import com.moins.fashion.world.payload.JwtResponse;
import com.moins.fashion.world.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/dressrentalsystem/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// save Admin
	@Operation(description = "To Create Admin info", summary = "Admin will be saved in the database")
	@ApiResponses(value = { @ApiResponse(description = "Admin Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@Valid @RequestBody AdminDto adminDto,
			BindingResult result) {
		return adminService.saveAdmin(adminDto, result);
	}

	// Admin Login
	@Operation(description = "To Login as admin", summary = "Admin will login")
	@ApiResponses(value = { @ApiResponse(description = "Admin logged in", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<JwtResponse>> login(@RequestBody JwtRequest request) {
		return this.adminService.login(request);
	}
}
