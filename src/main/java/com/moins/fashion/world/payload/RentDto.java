package com.moins.fashion.world.payload;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentDto {

	@NonNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Invalid date format..please use dd/MM/yyyy format..")
	private String rentDate;
	private List<Integer> dressesId;
}
