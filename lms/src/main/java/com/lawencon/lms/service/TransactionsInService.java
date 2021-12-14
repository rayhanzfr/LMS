package com.lawencon.lms.service;

import com.lawencon.lms.dto.transactionsin.GetAllTransactionsInResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInCodeResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInIdResDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInReqDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInResDto;

public interface TransactionsInService {

	GetAllTransactionsInResDto findAll() throws Exception;

	GetByTransactionsInIdResDto findById(String id) throws Exception;

	GetByTransactionsInCodeResDto findByCode(String code) throws Exception;

	SaveFullTransactionsInResDto save(SaveFullTransactionsInReqDto saveFullReq) throws Exception;
}
