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

import com.moins.fashion.world.dao.CustomerDao;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.payload.JwtRequest;
import com.moins.fashion.world.payload.JwtResponse;
import com.moins.fashion.world.util.JwtHelper;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	@Qualifier("customerManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	@Qualifier("customerDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<ResponseStructure<Customer>> createCustomer(Customer customer) {

		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		Customer receivedCustomer = customerDao.createCustomer(customer);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("Customer created");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Customer>> findById(int id) {

		Customer receivedCustomer = customerDao.findCustomerById(id);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("Customer fetched");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) {
		
		customer.setName(customer.getName());
		customer.setAddress(customer.getAddress());
		customer.setEmail(customer.getEmail());
		customer.setPassword(customer.getPassword());
		customer.setPhone(customer.getPhone());
		
		customer = customerDao.createCustomer(customer);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(customer);
		rs.setMessage("Customer updated");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Customer>> changePassword(String email, String old_password, String new_password) {

		doAuthenticate(email, old_password);
		Customer receivedCustomer = (Customer) userDetailsService.loadUserByUsername(email);
		receivedCustomer.setPassword(new_password);
		
		receivedCustomer = customerDao.createCustomer(receivedCustomer);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("password updated");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<JwtResponse>> customerLogin(JwtRequest request) {

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
