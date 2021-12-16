package com.lawencon.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.transactionsin.GetByTransactionsDetailInCodeResDto;
import com.lawencon.lms.service.TransactionsDetailInService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("transactions-detail-in")
public class TransactionsDetailInController {
	@Autowired
	private TransactionsDetailInService transactionsDetailInService;

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetByTransactionsDetailInCodeResDto.class))) })
	@GetMapping("/{code}")
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) throws Exception {
		GetByTransactionsDetailInCodeResDto allTransactionsIn = new GetByTransactionsDetailInCodeResDto();
		allTransactionsIn = transactionsDetailInService.findByTransactionInCode(null);
		return new ResponseEntity<>(allTransactionsIn, HttpStatus.OK);
	}

}
