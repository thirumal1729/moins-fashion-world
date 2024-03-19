package com.moins.fashion.world.entity;

import java.util.List;

import com.moins.fashion.world.util.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String rentDate;
	private double totalRentPrice;
	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	@JoinColumn
	private Customer customer;

	@OneToMany(mappedBy = "rent")
	private List<Dress> dresses;
}
