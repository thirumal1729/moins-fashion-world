package com.moins.fashion.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moins.fashion.world.dao.CustomerDao;
import com.moins.fashion.world.dto.CustomerDto;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.requsetmapper.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public ResponseEntity<ResponseStructure<Customer>> createCustomer(CustomerDto customerDto) {

		Customer receivedCustomer = CustomerMapper.mapToCustomer(customerDto);
		 receivedCustomer = customerDao.createCustomer(receivedCustomer);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("Customer created");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.CREATED);
	}

//	public ResponseEntity<ResponseStructure<Customer>> loginCustomer(String email, String password) {
//
//		Customer receivedCustomer = customerDao.loginCustomer(email, password);
//
//		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
//		rs.setData(receivedCustomer);
//		rs.setMessage("Customer fetched");
//		rs.setStatusCode(HttpStatus.OK.value());
//
//		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.OK);
//	}

	public ResponseEntity<ResponseStructure<Customer>> loginCustomerById(int id) {

		Customer receivedCustomer = customerDao.findCustomerById(id);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("Customer fetched");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(String email, String password, Customer customer) {

		Customer receivedCustomer = customerDao.loginCustomer(email, password);
		
		receivedCustomer.setName(customer.getName());
		receivedCustomer.setAddress(customer.getAddress());
		receivedCustomer.setEmail(customer.getEmail());
		receivedCustomer.setPassword(customer.getPassword());
		receivedCustomer.setPhone(customer.getPhone());
		
		receivedCustomer = customerDao.createCustomer(receivedCustomer);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("Customer updated");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Customer>> changePassword(String email, String old_password, String new_password) {

		Customer receivedCustomer = customerDao.loginCustomer(email, old_password);
		receivedCustomer.setPassword(new_password);
		
		receivedCustomer = customerDao.createCustomer(receivedCustomer);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("password updated");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.CREATED);
	}
	
	
	
}
