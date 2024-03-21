package com.moins.fashion.world.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
	@NotBlank(message="Name Should not be Blank")
	private String adminName;
	@Email(message = "Provide Valid Email..!")
	private String email;
	@Min(value = 6000000000l,message = "Provide Valid Phone Number")
	@Max(value = 9999999999l)
	private long phone;
	@Size(min = 8 ,message = "Password Size Should Contains Atleast 8 ")
	private String password;
}
