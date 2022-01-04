package com.lawencon.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.transactionsin.GetAllTransactionsInByUsersResDto;
import com.lawencon.lms.dto.transactionsin.GetAllTransactionsInResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInCodeResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInIdResDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInReqDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInResDto;
import com.lawencon.lms.dto.transactionsout.GetAllTransactionsOutByUsersResDto;
import com.lawencon.lms.service.TransactionsInService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("transactions-in")
public class TransactionsInController {
	@Autowired
	private TransactionsInService transactionsInService;

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetAllTransactionsInResDto.class))) })
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		GetAllTransactionsInResDto allTransactionsIn = new GetAllTransactionsInResDto();
		allTransactionsIn = transactionsInService.findAll();
		return new ResponseEntity<>(allTransactionsIn, HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllTransactionsInByUsersResDto.class)))
	@GetMapping("/users")
	public ResponseEntity<?> findAllByUsers() throws Exception {
		GetAllTransactionsInByUsersResDto result = transactionsInService.findAllByUsers();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = GetByTransactionsInIdResDto.class)))})
	@GetMapping("/id")
	public ResponseEntity<?> findById(@RequestParam(required = false, name = "id") String id) throws Exception {
		GetByTransactionsInIdResDto result = new GetByTransactionsInIdResDto();
		result = transactionsInService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = GetByTransactionsInCodeResDto.class)))})
	@GetMapping("/code")
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) throws Exception {
		GetByTransactionsInCodeResDto result = new GetByTransactionsInCodeResDto();
		result = transactionsInService.findByCode(code);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllTransactionsInByUsersResDto.class)))
	@GetMapping("/users")
	public ResponseEntity<?> findAllByUsers() throws Exception {
		GetAllTransactionsInByUsersResDto result = transactionsInService.findAllByUsers();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SaveFullTransactionsInResDto.class)))})
	@PostMapping
	public ResponseEntity<?> insertAll(@RequestBody SaveFullTransactionsInReqDto saveFullReq) throws Exception {
		SaveFullTransactionsInResDto result = new SaveFullTransactionsInResDto();
		result = transactionsInService.save(saveFullReq);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

}
