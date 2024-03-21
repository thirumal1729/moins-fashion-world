package com.moins.fashion.world.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.moins.fashion.world.dto.ResponseStructure;
import com.moins.fashion.world.entity.Dress;
import com.moins.fashion.world.payload.DressDto;
import com.moins.fashion.world.service.DressService;
import com.moins.fashion.world.util.DressSize;

@RestController
@RequestMapping("/dresses")
public class DressController {

	@Autowired
	private DressService dressService;

	@PostMapping
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<ResponseStructure<Dress>> saveDress(@RequestParam("image") MultipartFile file,
			@ModelAttribute DressDto dressDto) throws IOException {
		return this.dressService.saveDress(file, dressDto);
	}

	@GetMapping("/type/{type}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	public ResponseEntity<ResponseStructure<List<Dress>>> findByType(@PathVariable String type) {
		return dressService.findByType(type);
	}

	@GetMapping("/brandName/{brandName}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	public ResponseEntity<ResponseStructure<List<Dress>>> findByBrandName(@PathVariable String brandName) {
		return dressService.findByBrandName(brandName);
	}

	@GetMapping("/size/{size}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	public ResponseEntity<ResponseStructure<List<Dress>>> findBySize(@PathVariable DressSize size) {
		return dressService.findBySize(size);
	}

	@GetMapping("/dressId/{dressId}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	public ResponseEntity<ResponseStructure<Dress>> findById(@PathVariable int dressId) {
		return dressService.findById(dressId);
	}

	@GetMapping("/allDresses")
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<ResponseStructure<List<Dress>>> findAllDresses() {
		return dressService.findAllDresses();
	}

	@DeleteMapping("/{dressId}")
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<ResponseStructure<String>> deleteDress(@PathVariable int dressId) {
		return dressService.deleteDress(dressId);
	}
}
