package com.lawencon.lms.service;

import com.lawencon.lms.dto.transactionsout.GetAllTransactionsOutByUsersResDto;
import com.lawencon.lms.dto.transactionsout.GetAllTransactionsOutResDto;
import com.lawencon.lms.dto.transactionsout.GetByTransactionsOutCodeResDto;
import com.lawencon.lms.dto.transactionsout.GetByTransactionsOutIdResDto;
import com.lawencon.lms.dto.transactionsout.SaveFullTransactionsOutReqDto;
import com.lawencon.lms.dto.transactionsout.SaveFullTransactionsOutResDto;

public interface TransactionsOutService {
	SaveFullTransactionsOutResDto save(SaveFullTransactionsOutReqDto itemsReq) throws Exception;
	
	GetByTransactionsOutIdResDto findById(String id) throws Exception;
	
	GetByTransactionsOutCodeResDto findByCode(String code) throws Exception;
	
	GetAllTransactionsOutResDto findAll() throws Exception;
	
	GetAllTransactionsOutByUsersResDto findAllByUsers() throws Exception;
	
}
