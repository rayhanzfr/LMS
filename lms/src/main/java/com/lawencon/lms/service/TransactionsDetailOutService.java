package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.transactionsout.GetAllTransactionsDetailsOutResDto;
import com.lawencon.lms.dto.transactionsoutexpired.TransactionsOutExpired;
import com.lawencon.lms.model.TransactionsDetailOut;

public interface TransactionsDetailOutService {
	GetAllTransactionsDetailsOutResDto findByTransactionOutCode(String code) throws Exception;

	List<TransactionsOutExpired> getMoreThanExpired() throws Exception;

	List<TransactionsOutExpired> findAlmostExpired() throws Exception;
}
