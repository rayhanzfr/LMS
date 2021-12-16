package com.lawencon.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawencon.lms.dto.transactionsout.GetAllTransactionsDetailsOutResDto;
import com.lawencon.lms.service.TransactionsDetailOutService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("transactions-detail-out")
public class TransactionDetailOutController {
	@Autowired
	private TransactionsDetailOutService transactionsDetailOutService;
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = GetAllTransactionsDetailsOutResDto.class)))})
	@GetMapping
	public ResponseEntity<?>findByTransactionsOutCode(@RequestParam(value="transactionOutCode")String transactionsOutCode) throws Exception{
		GetAllTransactionsDetailsOutResDto result = transactionsDetailOutService.findByTransactionOutCode(transactionsOutCode);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
