package com.lawencon.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lawencon.lms.dto.companies.SaveCompaniesResDto;
import com.lawencon.lms.dto.companies.UpdateCompaniesResDto;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Items;
import com.lawencon.lms.service.CompaniesService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("companies")
public class CompaniesController {
	@Autowired
	private CompaniesService companiesService;
	
	protected <T> T convertToModel (String src, Class<T> clazz) throws Exception{
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        return new ObjectMapper()
                .registerModule(javaTimeModule)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .readValue(src, clazz);
    }
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Companies.class)))})
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		List<Companies> listCompanies = new ArrayList<Companies>();
		listCompanies = companiesService.findAll();
		return new ResponseEntity<>(listCompanies, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Companies.class)))})
	@GetMapping("/id")
	public ResponseEntity<?> findById(@RequestParam(required = false, name = "id") String id) throws Exception {
		Companies result = new Companies();
		result = companiesService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Companies.class)))})
	@GetMapping("/code")
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) throws Exception {
		Companies result = new Companies();
		result = companiesService.findByCode(code);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SaveCompaniesResDto.class)))})
	@PostMapping
	public ResponseEntity<?> insert(@RequestPart String data, @RequestPart(required=false) MultipartFile file) throws Exception {
		SaveCompaniesResDto companies = companiesService.save(convertToModel(data, Companies.class), file);
		return new ResponseEntity<>(companies, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UpdateCompaniesResDto.class)))})	
	@PutMapping
	public ResponseEntity<?> update(@RequestPart String data, @RequestPart(required=false) MultipartFile file) throws Exception {
		UpdateCompaniesResDto companies = companiesService.update(convertToModel(data, Companies.class), file);
		
		return new ResponseEntity<>(companies, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Companies.class)))})
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable String id) throws Exception {
		Boolean result = companiesService.removeById(id);
		if (result == false) {
			return new ResponseEntity<>("FAILED", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
	}
}
