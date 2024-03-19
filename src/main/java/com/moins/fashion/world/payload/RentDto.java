package com.moins.fashion.world.payload;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentDto {

	@Future(message = "Date must be in the future")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String rentDate;
	@DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
	@DecimalMax(value = "9999.99", message = "Price must be less than or equal to 9999.99")
	private double totalRentPrice;
}
