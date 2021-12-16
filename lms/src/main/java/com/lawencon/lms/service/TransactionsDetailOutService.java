package com.lawencon.lms.service;

import com.lawencon.lms.dto.transactionsout.GetAllTransactionsDetailsOutResDto;

public interface TransactionsDetailOutService {
	GetAllTransactionsDetailsOutResDto findByTransactionOutCode(String code) throws Exception;
}
