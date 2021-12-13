package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.TransactionsOut;

public interface TransactionsOutService {
	TransactionsOut saveOrUpdate(TransactionsOut items) throws Exception;
	
	TransactionsOut getById(String id) throws Exception;
	
	TransactionsOut getByCode(String code) throws Exception;
	
	List<TransactionsOut> getAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
