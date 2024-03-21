package com.moins.fashion.world.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Rent;
import com.moins.fashion.world.payload.RentDto;

import jakarta.validation.Valid;

@Service
public interface RentService {

	ResponseEntity<ResponseStructure<Rent>> saveRent(RentDto rentDto, String customerEmail,
			@Valid BindingResult result);

	ResponseEntity<ResponseStructure<List<Rent>>> getRentById(int rentId);

	ResponseEntity<ResponseStructure<List<Rent>>> getAllRentDetails(int adminId);

	ResponseEntity<ResponseStructure<String>> cancelRent(int rentId);

	ResponseEntity<ResponseStructure<String>> confirmRent(int rentId);

}
