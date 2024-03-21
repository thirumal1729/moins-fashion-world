package com.moins.fashion.world.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder // if I use @Builder then by difault all constructord are private , so we have to write both annotations to activate it
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Email
	@Column(unique = true)
	private String email;

	
	private long phone;

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
