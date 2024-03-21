package com.moins.fashion.world.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moins.fashion.world.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByEmail(String email);
	
}
