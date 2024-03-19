package com.moins.fashion.world.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.payload.DressDto;
import com.moins.fashion.world.util.DressSize;

@Service
public interface DressService {

	ResponseEntity<ResponseStructure<Dress>> saveDress(MultipartFile file, DressDto dressDto) throws IOException;

	ResponseEntity<ResponseStructure<List<Dress>>> findByType(String type);
	
	ResponseEntity<ResponseStructure<List<Dress>>> findByBrandName(String brandName);
	
	ResponseEntity<ResponseStructure<List<Dress>>> findBySize(DressSize size);
	
	ResponseEntity<ResponseStructure<Dress>> findById(int dressId);
}
