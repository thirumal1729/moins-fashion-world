package com.moins.fashion.world.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moins.fashion.world.entity.Admin;
import com.moins.fashion.world.repository.AdminRepository;

@Repository
public class AdminDao {
	@Autowired
	private AdminRepository adminRepository;

	// Save Admin
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	

}
