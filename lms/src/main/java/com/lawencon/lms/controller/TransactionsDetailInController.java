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

@RestController
@RequestMapping("transactions-detail-in")
public class TransactionsDetailInController {
	@Autowired
	private TransactionsDetailInService transactionsDetailInService;

	@GetMapping("/code/{code}")
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code)  {
		GetByTransactionsDetailInCodeResDto allTransactionsIn = new GetByTransactionsDetailInCodeResDto();
		try {
			allTransactionsIn = transactionsDetailInService.findByTransactionInCode(null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(allTransactionsIn, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(allTransactionsIn, HttpStatus.OK);
	}
	

}
