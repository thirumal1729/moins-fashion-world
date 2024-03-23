package com.moins.fashion.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.exception.MailFailedToSendException;
import com.moins.fashion.world.service.PasswordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/dressrentalsystem")
public class PasswordController {

	@Autowired
	private PasswordService passwordService;

	@PostMapping("/forgotpassword")
	ResponseEntity<ResponseStructure<String>> forgotPassword(@Valid @RequestParam String email){
		return passwordService.forgotPassword(email);
	}

	@PostMapping("/validatetoken")
	ResponseEntity<ResponseStructure<String>> validateToken(@RequestParam String token, @RequestParam String answer) {
		return passwordService.verify(token, answer);

	}
	@PostMapping("/setnewpassword")
	ResponseEntity<ResponseStructure<String>> updatePassword(@RequestParam String token,@RequestParam String new_password){
		return passwordService.setNewPassword(token, new_password);

	}
	
}