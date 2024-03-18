package com.moins.fashion.world.payload;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
	@NonNull
	private String adminName;
	@Email(message = "Provide Valid Email..!")
	private String email;
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long phone;
	@Size(min = 8)
	private String password;
}
