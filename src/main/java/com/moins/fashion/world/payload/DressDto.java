package com.moins.fashion.world.payload;

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
	
}
