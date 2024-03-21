package com.moins.fashion.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.moins.fashion.world.repository.CustomerRepository;

@Service("customerDetailsService")
public class CustomCustomerDetailsService implements UserDetailsService {

	@Autowired
	public CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.customerRepository.findByEmail(username).orElse(null);
	}

}
