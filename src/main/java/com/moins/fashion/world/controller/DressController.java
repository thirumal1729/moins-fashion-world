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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/dressrentalsystem/dresses")
public class DressController {

	@Autowired
	private DressService dressService;

	@Operation(description = "To Create Dress info", summary = "Dress will be saved in the database")
	@ApiResponses(value = { @ApiResponse(description = "Dress Created", responseCode = "201"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@PostMapping
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<ResponseStructure<Dress>> saveDress(@RequestParam("image") MultipartFile file,
			@ModelAttribute DressDto dressDto) throws IOException {
		return this.dressService.saveDress(file, dressDto);
	}

	@Operation(description = "To fetch Dress info by type", summary = "Dress will be fetched from the database")
	@ApiResponses(value = { @ApiResponse(description = "Dress fetched", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/type/{type}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	public ResponseEntity<ResponseStructure<List<Dress>>> findByType(@PathVariable String type) {
		return dressService.findByType(type);
	}

	@Operation(description = "To fetch Dress info by brand", summary = "Dress will be fetched from the database")
	@ApiResponses(value = { @ApiResponse(description = "Dress fetched", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/brandName/{brandName}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	public ResponseEntity<ResponseStructure<List<Dress>>> findByBrandName(@PathVariable String brandName) {
		return dressService.findByBrandName(brandName);
	}

	@Operation(description = "To fetch Dress info by size", summary = "Dress will be fetched from the database")
	@ApiResponses(value = { @ApiResponse(description = "Dress fetched", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/size/{size}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	public ResponseEntity<ResponseStructure<List<Dress>>> findBySize(@PathVariable DressSize size) {
		return dressService.findBySize(size);
	}

	@Operation(description = "To fetch Dress info by ID", summary = "Dress will be fetched from the database")
	@ApiResponses(value = { @ApiResponse(description = "Dress fetched", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/dressId/{dressId}")
	@PreAuthorize(value = "hasRole('CUSTOMER')")
	public ResponseEntity<ResponseStructure<Dress>> findById(@PathVariable int dressId) {
		return dressService.findById(dressId);
	}

	@Operation(description = "To fetch all Dresses info", summary = "Dresses will be fetched from the database")
	@ApiResponses(value = { @ApiResponse(description = "Dress fetched", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@GetMapping("/allDresses")
	public ResponseEntity<ResponseStructure<List<Dress>>> findAllDresses() {
		return dressService.findAllDresses();
	}

	@Operation(description = "To remove Dress info by id", summary = "Dress will be removed from the database")
	@ApiResponses(value = { @ApiResponse(description = "Dress removed", responseCode = "200"),
			@ApiResponse(content = @Content(), responseCode = "400") })
	@DeleteMapping("/{dressId}")
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<ResponseStructure<String>> deleteDress(@PathVariable int dressId) {
		return dressService.deleteDress(dressId);
	}
}
