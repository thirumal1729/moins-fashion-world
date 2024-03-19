package com.moins.fashion.world.payload;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
	private List<Integer> dressesId;
}
