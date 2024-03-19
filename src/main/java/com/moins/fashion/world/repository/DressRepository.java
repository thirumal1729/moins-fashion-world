package com.moins.fashion.world.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.util.DressSize;

public interface DressRepository extends JpaRepository<Dress, Integer> {

	Dress findByType(String type);
	
	Dress findByBrandName(String brandName);
	
	Dress findByDressSize(DressSize dressSize);
	
}
