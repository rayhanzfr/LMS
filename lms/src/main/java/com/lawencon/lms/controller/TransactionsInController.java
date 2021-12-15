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

import com.lawencon.lms.dto.transactionsin.GetAllTransactionsInResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInCodeResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInIdResDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInReqDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInResDto;
import com.lawencon.lms.service.TransactionsInService;

@RestController
@RequestMapping("transactions-in")
public class TransactionsInController {
	@Autowired
	private TransactionsInService transactionsInService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		GetAllTransactionsInResDto allTransactionsIn = new GetAllTransactionsInResDto();
		try {
			allTransactionsIn = transactionsInService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(allTransactionsIn, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(allTransactionsIn, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@RequestParam(required = false, name = "id" ) String id) {
		GetByTransactionsInIdResDto result = new GetByTransactionsInIdResDto();
		try {
			result = transactionsInService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/code/{code}")
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) {
		GetByTransactionsInCodeResDto result = new GetByTransactionsInCodeResDto();
		try {
			result = transactionsInService.findByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insertAll(@RequestBody SaveFullTransactionsInReqDto saveFullReq) {
		SaveFullTransactionsInResDto result = new SaveFullTransactionsInResDto();
		try {
			result = transactionsInService.save(saveFullReq);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	

}
