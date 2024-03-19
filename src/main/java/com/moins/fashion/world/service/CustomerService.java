package com.moins.fashion.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moins.fashion.world.dao.CustomerDao;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public ResponseEntity<ResponseStructure<Customer>> createCustomer(Customer customer) {

		Customer receivedCustomer = customerDao.createCustomer(customer);

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

		Customer receivedCustomer = customerDao.loginById(id);

		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		rs.setData(receivedCustomer);
		rs.setMessage("Customer fetched");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<Customer>>(rs, HttpStatus.OK);
	}
}
