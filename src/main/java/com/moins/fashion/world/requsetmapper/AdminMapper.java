package com.moins.fashion.world.requsetmapper;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.moins.fashion.world.entity.Admin;
import com.moins.fashion.world.payload.AdminDto;

public class AdminMapper {
	public static Admin mapToDress(AdminDto adminDto,PasswordEncoder passwordEncoder) {

		return Admin.builder().adminName(adminDto.getAdminName()).email(adminDto.getEmail())
				.password(passwordEncoder.encode(adminDto.getPassword())).phone(adminDto.getPhone()).build();
	}
}
