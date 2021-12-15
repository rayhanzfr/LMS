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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.dto.companies.SaveCompaniesResDto;
import com.lawencon.lms.dto.companies.UpdateCompaniesResDto;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.service.CompaniesService;

@RestController
@RequestMapping("companies")
public class CompaniesController {
	@Autowired
	private CompaniesService companiesService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Companies> listCompanies = new ArrayList<Companies>();
		try {
			listCompanies = companiesService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listCompanies, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(listCompanies, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<?> findById(@RequestParam(required = false, name = "id" ) String id) {
		Companies result = new Companies();
		try {
			result = companiesService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) {
		Companies result = new Companies();
		try {
			result = companiesService.findByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Companies companies, MultipartFile files) {
		SaveCompaniesResDto result = new SaveCompaniesResDto();
		try {
			result = companiesService.save(companies, files);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Companies companies) {
		UpdateCompaniesResDto result = new UpdateCompaniesResDto();
		try {
			result = companiesService.update(companies);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable String id){
		Boolean result = null;
		try {
			result = companiesService.removeById(id);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	} 
}
