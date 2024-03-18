package com.moins.fashion.world.payload;

import com.moins.fashion.world.util.DressSize;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DressDto {

	private String type;
	private double priceMRP;
	private double rentPrice;
	private double depositPrice;
	private String brandName;
	
	@Enumerated(EnumType.STRING)
	private DressSize dressSize;
	
}
