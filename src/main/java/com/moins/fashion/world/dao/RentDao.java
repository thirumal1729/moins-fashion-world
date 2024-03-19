package com.moins.fashion.world.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moins.fashion.world.entity.Rent;
import com.moins.fashion.world.repository.RentRepository;

@Repository
public class RentDao {

	@Autowired
	RentRepository rentRepository;

	public Rent saveRent(Rent rent) {
		return rentRepository.save(rent);
	}

	public Rent getRentById(int rentId) {
		return rentRepository.findById(rentId).get();
	}

	public List<Rent> getAllRentDetails() {
		return rentRepository.findAll();
	}

}
