package com.lawencon.lms.service;

import com.lawencon.lms.dto.transactionsin.GetByTransactionsDetailInCodeResDto;

public interface TransactionsDetailInService {
	GetByTransactionsDetailInCodeResDto findByTransactionInCode(String code) throws Exception;
}
