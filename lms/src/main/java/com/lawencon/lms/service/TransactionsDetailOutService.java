package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.transactionsout.GetAllTransactionsDetailsOutResDto;
import com.lawencon.lms.dto.transactionsoutexpired.TransactionsOutExpiredResDto;

public interface TransactionsDetailOutService {
	GetAllTransactionsDetailsOutResDto findByTransactionOutCode(String code) throws Exception;

	List<TransactionsOutExpiredResDto> getMoreThanExpired() throws Exception;

	List<TransactionsOutExpiredResDto> findAlmostExpired() throws Exception;
	
	public void sendReminder() throws Exception;
}
