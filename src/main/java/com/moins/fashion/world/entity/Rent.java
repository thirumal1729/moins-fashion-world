package com.moins.fashion.world.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rent {

	private int id;
	private String rentDate;
	private double totalRentPrice;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
	
	@OneToMany(mappedBy = "rent")
	private List<Dress> dresses;
}
