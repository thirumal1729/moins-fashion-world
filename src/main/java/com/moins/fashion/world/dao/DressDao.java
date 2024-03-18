package com.moins.fashion.world.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.repository.DressRepository;

@Repository
public class DressDao {

	@Autowired
	private DressRepository dressRepository;
	
	public Dress saveDress(Dress dress) {
		return this.dressRepository.save(dress);
	}
	
}
