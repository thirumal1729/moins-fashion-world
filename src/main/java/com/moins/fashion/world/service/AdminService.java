package com.moins.fashion.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moins.fashion.world.dao.AdminDao;

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
