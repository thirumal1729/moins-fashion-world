package com.moins.fashion.world.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Rent;
import com.moins.fashion.world.payload.RentDto;

@Service
public interface RentService {

	ResponseEntity<ResponseStructure<Rent>> saveRent(RentDto rentDto);

	ResponseEntity<ResponseStructure<Rent>> getRentById(int rentId, int customerId);

	ResponseEntity<ResponseStructure<List<Rent>>> getAllRentDetails(int adminId);

	ResponseEntity<ResponseStructure<String>> cancelRent(int rentId);

	ResponseEntity<ResponseStructure<String>> confirmRent(int rentId);

}
