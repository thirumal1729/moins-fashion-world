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
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull
	private long phone;
	
	@NotNull
	private String password;
	private String address;

	@OneToMany(mappedBy = "customer")
	List<Rent> rents;
}
