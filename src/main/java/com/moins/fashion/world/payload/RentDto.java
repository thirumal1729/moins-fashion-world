package com.moins.fashion.world.payload;

import java.util.List;

import io.micrometer.common.lang.NonNull;
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
	private String rentDate;
	private List<Integer> dressesId;
}
