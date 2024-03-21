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
import com.moins.fashion.world.dto.CustomerDto;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.payload.JwtRequest;
import com.moins.fashion.world.payload.JwtResponse;
import com.moins.fashion.world.requsetmapper.CustomerMapper;
import com.moins.fashion.world.util.JwtHelper;

@Service
public class CustomerServiceImpl implements CustomerService {

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

	@Override
	public ResponseEntity<ResponseStructure<Customer>> createCustomer(CustomerDto customerDto) {

		Customer receivedCustomer = CustomerMapper.mapToCustomer(customerDto);
		 receivedCustomer = customerDao.createCustomer(receivedCustomer);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("Customer created");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(int id, CustomerDto customerDto) {

		Customer receivedCustomer =customerDao.findCustomerById(id); 
		 Customer receivedCustomer2=CustomerMapper.mapToCustomer(customerDto);
		
		
		 if(customerDto.getName()!=null)
		 {
			 receivedCustomer.setName(receivedCustomer2.getName());
		 }
		 if(customerDto.getAddress()!=null)
		 {
			 receivedCustomer.setAddress(receivedCustomer2.getAddress());
		 }
		 if(customerDto.getEmail()!=null)
		 {
			 receivedCustomer.setEmail(receivedCustomer2.getEmail());
		 }
		 if(customerDto.getPassword()!=null)
		 {
			 receivedCustomer.setPassword(receivedCustomer2.getPassword());
		 }
		 if(customerDto.getPhone()!= 0)
		 {
			 receivedCustomer.setPhone(receivedCustomer2.getPhone());
		 }
		 if(customerDto.getAlternativeEmail()!= null)
		 {
			 receivedCustomer.setAlternativeEmail(receivedCustomer2.getAlternativeEmail());
		 }
		 if(customerDto.getSecurityQuestion()!= null)
		 {
			 receivedCustomer.setSecurityQuestion(receivedCustomer2.getSecurityQuestion());
		 }
		 if(customerDto.getAnswer()!= null)
		 {
			 receivedCustomer.setAnswer(receivedCustomer2.getAnswer());
		 }
		 
		receivedCustomer = customerDao.createCustomer(receivedCustomer);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("Customer updated");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<Customer>> changePassword(int id, String new_password) {

		Customer receivedCustomer = customerDao.findCustomerById(id);
		receivedCustomer.setPassword(new_password);
		
		receivedCustomer = customerDao.createCustomer(receivedCustomer);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("password updated");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<Customer>> findById(int id) {

		Customer receivedCustomer = customerDao.findCustomerById(id);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("Customer fetched");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.OK);
	}

	@Override
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
