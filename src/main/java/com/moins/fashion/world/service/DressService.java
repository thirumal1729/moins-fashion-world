package com.moins.fashion.world.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.payload.DressDto;

@Service
public interface DressService {

	ResponseEntity<ResponseStructure<Dress>> saveDress(MultipartFile file, DressDto dressDto) throws IOException;
	
}
