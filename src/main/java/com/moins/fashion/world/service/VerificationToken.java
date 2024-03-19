package com.moins.fashion.world.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moins.fashion.world.dao.CustomerDao;
import com.moins.fashion.world.entity.Customer;
@Service
public class VerificationToken {

	@Autowired
	private CustomerDao customerDao;
	
	public String generateToken(Customer customer) {
		String token = UUID.randomUUID().toString();
		customer.setMyToken(token);
		customerDao.createCustomer(customer);
		
		return token;
	}
	
	public Customer findCustomerByToken(String token) {
		return customerDao.findByToken(token);
	}
	
}
