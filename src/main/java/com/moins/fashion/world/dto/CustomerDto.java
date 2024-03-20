package com.moins.fashion.world.dto;

import java.util.List;

import com.moins.fashion.world.entity.Rent;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CustomerDto {
	private String name;

	@Email
	@Column(unique = true)
	private String email;

	@NotNull
	private long phone; 

	@NotNull
	private String password;

	private String address;
	private String securityQuestion;
	private String answer;
	private boolean is_verified = false;
	private String myToken;
	private String alternativeEmail; 

	@OneToMany(mappedBy = "customer")
	List<Rent> rents;
}
