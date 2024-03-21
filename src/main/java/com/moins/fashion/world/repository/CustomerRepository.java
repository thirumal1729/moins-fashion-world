package com.moins.fashion.world.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moins.fashion.world.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Optional<Customer> findByEmail(String email);
	
	Customer findByMyToken(String token);
}
