package com.moins.fashion.world.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moins.fashion.world.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

//	Customer findbyEmailandPassword(String email, String password);
	
	
}
