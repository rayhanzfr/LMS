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

import com.lawencon.lms.dto.invoices.SaveInvoicesResDto;
import com.lawencon.lms.dto.invoices.UpdateInvoicesResDto;
import com.lawencon.lms.model.Invoices;
import com.lawencon.lms.service.InvoicesService;

@RestController
@RequestMapping("invoices")
public class InvoicesController {
	@Autowired
	private InvoicesService invoicesService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Invoices> listInvoices = new ArrayList<Invoices>();
		try {
			listInvoices = invoicesService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listInvoices, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(listInvoices, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@RequestParam(required = false, name = "id" ) String id) {
		Invoices result = new Invoices();
		try {
			result = invoicesService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/code/{code}")
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) {
		Invoices result = new Invoices();
		try {
			result = invoicesService.findByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Invoices invoices, MultipartFile files) {
		SaveInvoicesResDto result = new SaveInvoicesResDto();
		try {
			result = invoicesService.save(invoices);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Invoices invoices) {
		UpdateInvoicesResDto result = new UpdateInvoicesResDto();
		try {
			result = invoicesService.update(invoices);
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
			result = invoicesService.removeById(id);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	} 
}
