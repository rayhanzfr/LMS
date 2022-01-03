package com.lawencon.lms.service;

import com.lawencon.lms.dto.transactionsin.GetAllTransactionsInByUsersResDto;
import com.lawencon.lms.dto.transactionsin.GetAllTransactionsInResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInCodeResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInIdResDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInReqDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInResDto;
import com.lawencon.lms.dto.transactionsout.GetAllTransactionsOutByUsersResDto;

public interface TransactionsInService {

	GetAllTransactionsInResDto findAll() throws Exception;

	GetByTransactionsInIdResDto findById(String id) throws Exception;

	GetByTransactionsInCodeResDto findByCode(String code) throws Exception;
	
	GetAllTransactionsInByUsersResDto findAllByUsers() throws Exception;

	SaveFullTransactionsInResDto save(SaveFullTransactionsInReqDto saveFullReq) throws Exception;
}
