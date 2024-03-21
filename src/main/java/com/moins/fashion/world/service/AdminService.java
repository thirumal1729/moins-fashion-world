package com.moins.fashion.world.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Admin;
import com.moins.fashion.world.payload.AdminDto;
import com.moins.fashion.world.payload.JwtRequest;
import com.moins.fashion.world.payload.JwtResponse;

@Service
public interface AdminService {
	ResponseEntity<ResponseStructure<Admin>> saveAdmin(AdminDto adminDto, BindingResult result);
	
	ResponseEntity<ResponseStructure<JwtResponse>> login(JwtRequest request);
}
