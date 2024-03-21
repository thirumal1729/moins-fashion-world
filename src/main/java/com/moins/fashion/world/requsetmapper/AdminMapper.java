package com.moins.fashion.world.requsetmapper;

import com.moins.fashion.world.entity.Admin;
import com.moins.fashion.world.payload.AdminDto;

public class AdminMapper {
	public static Admin mapToDress(AdminDto adminDto) {

		return Admin.builder().adminName(adminDto.getAdminName()).email(adminDto.getEmail())
				.password(adminDto.getPassword()).phone(adminDto.getPhone()).build();
	}
}
