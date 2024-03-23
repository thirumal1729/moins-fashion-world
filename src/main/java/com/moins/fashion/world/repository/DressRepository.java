package com.moins.fashion.world.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.entity.Rent;
import com.moins.fashion.world.util.DressSize;

public interface DressRepository extends JpaRepository<Dress, Integer> {

	List<Dress> findByType(String type);

	List<Dress> findByBrandName(String brandName);

	List<Dress> findByDressSize(DressSize dressSize);
	
	List<Rent> findByDressId(int dressId);

}
