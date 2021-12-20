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

import com.lawencon.lms.dto.invoices.SaveInvoicesResDto;
import com.lawencon.lms.dto.invoices.UpdateInvoicesResDto;
import com.lawencon.lms.model.Invoices;
import com.lawencon.lms.service.InvoicesService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("invoices")
public class InvoicesController {
	@Autowired
	private InvoicesService invoicesService;

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Invoices.class)))})
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		List<Invoices> listInvoices = new ArrayList<Invoices>();
		listInvoices = invoicesService.findAll();
		return new ResponseEntity<>(listInvoices, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Invoices.class)))})
	@GetMapping("/id")
	public ResponseEntity<?> findById(@RequestParam(required = false, name = "id") String id) throws Exception {
		Invoices result = new Invoices();
		result = invoicesService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Invoices.class)))})
	@GetMapping("/code")
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) throws Exception {
		Invoices result = new Invoices();
		result = invoicesService.findByCode(code);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SaveInvoicesResDto.class)))})
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Invoices invoices) throws Exception {
		SaveInvoicesResDto result = new SaveInvoicesResDto();
		result = invoicesService.save(invoices);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UpdateInvoicesResDto.class)))})
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Invoices invoices) throws Exception {
		UpdateInvoicesResDto result = new UpdateInvoicesResDto();
		result = invoicesService.update(invoices);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Invoices.class)))})
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable String id) throws Exception {
		Boolean result = invoicesService.removeById(id);
		if (result == false) {
			return new ResponseEntity<>("FAILED", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
	}
}
