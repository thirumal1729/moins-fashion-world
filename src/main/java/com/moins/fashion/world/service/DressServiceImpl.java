package com.moins.fashion.world.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import com.moins.fashion.world.payload.DressDto;

@Service
public class DressServiceImpl implements DressService {

	@Value("${project.image}")
	private String path;
	
	@Autowired
	private DressDao dressDao;

	@Override
	public ResponseEntity<ResponseStructure<Dress>> saveDress(MultipartFile file, DressDto dressDto)
			throws IOException {

		// File name
		String name = file.getOriginalFilename();

		// generate random Id for each photo

		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

		// Fullpath
		String filePath = path + File.separator + fileName1;

		// create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));

		Dress dress = Dress.builder().type(dressDto.getType()).priceMRP(dressDto.getPriceMRP()).rentPrice(dressDto.getRentPrice())
				.depositPrice(dressDto.getDepositPrice()).brandName(dressDto.getBrandName()).dressImage(filePath)
				.dressSize(dressDto.getDressSize()).build();

		this.dressDao.saveDress(dress);
		
		ResponseStructure<Dress> responseStructure = new ResponseStructure<Dress>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(dress);
		
		return new ResponseEntity<ResponseStructure<Dress>>(responseStructure, HttpStatus.CREATED);
	}

}
