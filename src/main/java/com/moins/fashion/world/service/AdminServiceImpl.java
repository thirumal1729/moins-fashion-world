package com.moins.fashion.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.moins.fashion.world.dao.AdminDao;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Admin;
import com.moins.fashion.world.exception.ValidationException;
import com.moins.fashion.world.payload.AdminDto;
import com.moins.fashion.world.payload.JwtRequest;
import com.moins.fashion.world.payload.JwtResponse;
import com.moins.fashion.world.util.JwtHelper;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	@Autowired
	@Qualifier("adminManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	@Qualifier("adminDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
				.password(passwordEncoder.encode(adminDto.getPassword())).phone(adminDto.getPhone()).build();

		adminObject = adminDao.saveAdmin(adminObject);

		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Created");
		responseStructure.setData(adminObject);

		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.CREATED);
	}

	// user login operations
	@Override
	public ResponseEntity<ResponseStructure<JwtResponse>> login(JwtRequest request) {

		doAuthenticate(request.getEmail(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);

		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();

		ResponseStructure<JwtResponse> responseStructure = new ResponseStructure<JwtResponse>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Logged in successfully...!");
		responseStructure.setData(response);

		return new ResponseEntity<ResponseStructure<JwtResponse>>(responseStructure, HttpStatus.OK);

	}

	// authenticate details present in database
	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticationManager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}
}
