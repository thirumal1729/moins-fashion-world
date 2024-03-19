package com.moins.fashion.world.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.moins.fashion.world.dao.AdminDao;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Admin;
import com.moins.fashion.world.exception.ValidationException;
import com.moins.fashion.world.payload.AdminDto;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	// Save Admin
	@Override
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(AdminDto adminDto, BindingResult result) {
		if (result.hasErrors()) {
			String message = "";
			for (FieldError error : result.getFieldErrors()) {
				message = message + error.getDefaultMessage() + "\n";
			}
			throw new ValidationException(message);
		}
		Admin adminObject = Admin.builder().adminName(adminDto.getAdminName()).email(adminDto.getEmail())
				.password(adminDto.getPassword()).phone(adminDto.getPhone()).build();

		adminObject = adminDao.saveAdmin(adminObject);

		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Created");
		responseStructure.setData(adminObject);

		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.CREATED);
	}
}
