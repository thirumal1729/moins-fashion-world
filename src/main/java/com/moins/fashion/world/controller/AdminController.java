package com.moins.fashion.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Admin;
import com.moins.fashion.world.payload.AdminDto;
import com.moins.fashion.world.payload.JwtRequest;
import com.moins.fashion.world.payload.JwtResponse;
import com.moins.fashion.world.service.AdminService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/fashion")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	// save Admin
	@PostMapping("/admin")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@Valid @RequestBody AdminDto adminDto,
			BindingResult result) {
		return adminService.saveAdmin(adminDto, result);
	}

	// Admin Login
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<JwtResponse>> login(@RequestBody JwtRequest request){
		return this.adminService.login(request);
	}
}
