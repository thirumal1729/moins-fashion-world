package com.moins.fashion.world.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.payload.DressDto;
import com.moins.fashion.world.service.DressService;

@RestController
@RequestMapping("/dresses")
public class DressController {

	@Autowired
	private DressService dressService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Dress>> saveDress(@RequestParam("image") MultipartFile file, @ModelAttribute DressDto dressDto) throws IOException {
		return this.dressService.saveDress(file, dressDto);
	}
	
}
