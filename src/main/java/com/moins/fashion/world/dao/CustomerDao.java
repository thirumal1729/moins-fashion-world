package com.moins.fashion.world.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.repository.CustomerRepository;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer createCustomer (Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer findCustomerById(int id) {
		return customerRepository.findById(id).orElse(null);
	}
	
	public Customer findbyEmail(String email) {
		return customerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException());
	}
	
	public Customer findByToken(String token) {
		return customerRepository.findByMyToken(token);
		
	}
}
