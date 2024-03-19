package com.moins.fashion.world.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.exception.DressesNotAvailableException;
import com.moins.fashion.world.repository.DressRepository;
import com.moins.fashion.world.util.DressSize;

@Repository
public class DressDao {

	@Autowired
	private DressRepository dressRepository;

	// save Dress
	public Dress saveDress(Dress dress) {
		return this.dressRepository.save(dress);
	}

	// searching dress by dress type
	public List<Dress> findByType(String type) {
		return this.dressRepository.findByType(type);
	}

	// searching dress by brand name
	public List<Dress> findByBrandName(String brandName) {
		return this.dressRepository.findByBrandName(brandName);
	}

	// searching dress by size
	public List<Dress> findBySize(DressSize size) {
		return this.dressRepository.findByDressSize(size);
	}

	// find by id
	public Dress findById(int dressId) {
		return this.dressRepository.findById(dressId).orElseThrow(() -> new RuntimeException());
	}

	// delete Dress
	public boolean deleteDress(int dressId) {
		Dress dress = findById(dressId);
		if (dress != null) {
			this.dressRepository.delete(dress);
			return true;
		} else {
			throw new DressesNotAvailableException();
		}
	}
}
