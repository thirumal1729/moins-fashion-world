package com.moins.fashion.world.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.moins.fashion.world.dao.DressDao;
import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.exception.DressNotFoundException;
import com.moins.fashion.world.exception.DressesNotAvailableException;
import com.moins.fashion.world.payload.DressDto;
import com.moins.fashion.world.util.DressSize;

@Service
public class DressServiceImpl implements DressService {

	@Value("${project.image}")
	private String path;

	@Autowired
	private DressDao dressDao;
	
	private static List<String> ALLOWED_EXTENTIONS = Arrays.asList(".jpg", ".jpeg", ".png");

	@Override
	public ResponseEntity<ResponseStructure<Dress>> saveDress(MultipartFile file, DressDto dressDto)
			throws IOException {

		// File name
		String name = file.getOriginalFilename();

		
		String extention = name.substring(name.lastIndexOf("."));
		
		if(!ALLOWED_EXTENTIONS.contains(extention)) {
			throw null;
		}
		
		// generate random Id for each photo
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(extention);

		// Fullpath
		String filePath = path + File.separator + fileName1;

		// create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));

		Dress dress = Dress.builder().type(dressDto.getType()).priceMRP(dressDto.getPriceMRP())
				.rentPrice(dressDto.getRentPrice()).depositPrice(dressDto.getDepositPrice())
				.brandName(dressDto.getBrandName()).dressImage(filePath).dressSize(dressDto.getDressSize()).build();

		this.dressDao.saveDress(dress);

		ResponseStructure<Dress> responseStructure = new ResponseStructure<Dress>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(dress);

		return new ResponseEntity<ResponseStructure<Dress>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Dress>>> findByType(String type) {
		List<Dress> dresses = this.dressDao.findByType(type);

		if (!dresses.isEmpty()) {
			ResponseStructure<List<Dress>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(dresses);

			return new ResponseEntity<ResponseStructure<List<Dress>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new DressesNotAvailableException();
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Dress>>> findByBrandName(String brandName) {
		List<Dress> dresses = this.dressDao.findByBrandName(brandName);

		if (!dresses.isEmpty()) {
			ResponseStructure<List<Dress>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(dresses);

			return new ResponseEntity<ResponseStructure<List<Dress>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new DressesNotAvailableException();
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Dress>>> findBySize(DressSize size) {
		List<Dress> dresses = this.dressDao.findBySize(size);

		if (!dresses.isEmpty()) {
			ResponseStructure<List<Dress>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(dresses);

			return new ResponseEntity<ResponseStructure<List<Dress>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new DressesNotAvailableException();
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Dress>> findById(int dressId) {
		Dress dress = this.dressDao.findById(dressId);

		if (dress != null) {
			ResponseStructure<Dress> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(dress);

			return new ResponseEntity<ResponseStructure<Dress>>(responseStructure, HttpStatus.OK);
		} else {
			throw new DressNotFoundException();
		}
	}

}
