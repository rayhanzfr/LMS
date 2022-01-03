package com.lawencon.lms.service;

import com.lawencon.lms.dto.transactionsin.GetAllTransactionsDetailInResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsDetailInCodeResDto;

public interface TransactionsDetailInService {
	GetAllTransactionsDetailInResDto findByTransactionInCode(String code) throws Exception;
}
