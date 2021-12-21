package com.lawencon.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.transactionsout.GetAllTransactionsOutResDto;
import com.lawencon.lms.dto.transactionsout.GetByTransactionsOutIdResDto;
import com.lawencon.lms.dto.transactionsout.SaveFullTransactionsOutReqDto;
import com.lawencon.lms.dto.transactionsout.SaveFullTransactionsOutResDto;
import com.lawencon.lms.service.TransactionsOutService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("transactions-out")
public class TransactionsOutController extends BaseController{
	@Autowired
	private TransactionsOutService transactionsOutService;

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllTransactionsOutResDto.class)))
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		GetAllTransactionsOutResDto result = transactionsOutService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByTransactionsOutIdResDto.class)))
	@GetMapping("/id")
	public ResponseEntity<?> findById(@RequestParam("id") String id) throws Exception {
		GetByTransactionsOutIdResDto result = transactionsOutService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = SaveFullTransactionsOutResDto.class)))
	@PostMapping
	public ResponseEntity<?> save(@RequestBody SaveFullTransactionsOutReqDto data) throws Exception {
		SaveFullTransactionsOutResDto result = transactionsOutService.save(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

}
