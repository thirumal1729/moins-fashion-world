package com.moins.fashion.world.requsetmapper;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.moins.fashion.world.dto.CustomerDto;
import com.moins.fashion.world.entity.Customer;

public class CustomerMapper {

	public static Customer mapToCustomer(CustomerDto customerDto, PasswordEncoder passwordEncoder) {
		return Customer.builder().name(customerDto.getName()).address(customerDto.getAddress())
				.email(customerDto.getEmail()).phone(customerDto.getPhone())
				.password(passwordEncoder.encode(customerDto.getPassword()))
				.securityQuestion(customerDto.getSecurityQuestion()).answer(customerDto.getAnswer())
				.is_verified(customerDto.is_verified()).myToken(customerDto.getMyToken())
				.alternativeEmail(customerDto.getAlternativeEmail()).build();
	}

}
