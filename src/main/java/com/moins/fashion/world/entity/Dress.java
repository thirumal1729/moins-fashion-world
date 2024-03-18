package com.moins.fashion.world.entity;

import com.moins.fashion.world.util.DressSize;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dress {

	@Id
	private int dressId;
	private String type;
	private double priceMRP;
	private double rentPrice;
	private double depositPrice;
	private String dressImage;
	private String brandName;
	
	@Enumerated(EnumType.STRING)
	private DressSize dressSize;
	
	@ManyToOne
	@JoinColumn
	private Rent rent;
	
}
