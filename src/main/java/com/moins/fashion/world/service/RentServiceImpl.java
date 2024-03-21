package com.moins.fashion.world.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moins.fashion.world.dao.CustomerDao;
import com.moins.fashion.world.dao.DressDao;
import com.moins.fashion.world.dao.RentDao;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Customer;
import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.entity.Rent;
import com.moins.fashion.world.exception.RentDetailsNotFoundException;
import com.moins.fashion.world.payload.RentDto;
import com.moins.fashion.world.requsetmapper.RentMapper;
import com.moins.fashion.world.util.Status;

@Service
public class RentServiceImpl implements RentService {

	@Autowired
	RentDao rentDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	DressDao dressDao;

	@Override
	public ResponseEntity<ResponseStructure<Rent>> saveRent(RentDto rentDto, String customerEmail) {

		Rent rent = RentMapper.mapToRent(rentDto, dressDao);
		Customer customer = customerDao.findbyEmail(customerEmail);
		rent.setCustomer(customer);

		rent = this.rentDao.saveRent(rent);

		List<Dress> dresses = rent.getDresses();

		for (Dress dress : dresses) {

			dress.setRent(rent);
			dressDao.saveDress(dress);

		}

		ResponseStructure<Rent> responseStructure = new ResponseStructure<Rent>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(rent);

		return new ResponseEntity<ResponseStructure<Rent>>(responseStructure, HttpStatus.CREATED);

	}

	// Validation to be provided by the customer
	@Override
	public ResponseEntity<ResponseStructure<List<Rent>>> getRentById(int customerId){

		Customer customer = customerDao.findCustomerById(customerId);
		List<Rent> rentList = customer.getRents();
		if(rentList!=null)
		{
		ResponseStructure<List<Rent>> responseStructure = new ResponseStructure<List<Rent>>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(rentList);

		return new ResponseEntity<ResponseStructure<List<Rent>>>(responseStructure, HttpStatus.OK);
		}
		else {
			throw new RentDetailsNotFoundException();
		}

	}

	// Validation to be provided by the customer
	@Override
	public ResponseEntity<ResponseStructure<List<Rent>>> getAllRentDetails(int adminId) {

		List<Rent> rentDetailsList = rentDao.getAllRentDetails();

		ResponseStructure<List<Rent>> responseStructure = new ResponseStructure<List<Rent>>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(rentDetailsList);

		return new ResponseEntity<ResponseStructure<List<Rent>>>(responseStructure, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponseStructure<String>> cancelRent(int rentId) {

		Rent rent = rentDao.getRentById(rentId);
		rent.setStatus(Status.CANCELLED);
		rent = rentDao.saveRent(rent);

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setData("CANCELLED");

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponseStructure<String>> confirmRent(int rentId) {

		Rent rent = rentDao.getRentById(rentId);
		rent.setStatus(Status.CONFIRMED);
		rent = rentDao.saveRent(rent);

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setData("CONFIRMED");

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

	}
}
