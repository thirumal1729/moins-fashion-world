package com.moins.fashion.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.moins.fashion.world.dao.AdminDao;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Admin;
import com.moins.fashion.world.exception.ValidationException;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	// Save Admin
//	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin, BindingResult result) {
//		if (result.hasErrors()) {
//			String message = "";
//			for (FieldError error : result.getFieldErrors()) {
//				message = message + error.getDefaultMessage() + "\n";
//			}
//			throw new ValidationException(message);
//		}
//		Admin adminObject=new Admin();
//		
//	}
}
