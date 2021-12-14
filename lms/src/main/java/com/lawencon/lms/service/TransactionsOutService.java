package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.TransactionsOut;

public interface TransactionsOutService {
	TransactionsOut save(TransactionsOut items) throws Exception;
	
	TransactionsOut update(TransactionsOut items) throws Exception;
	
	TransactionsOut findById(String id) throws Exception;
	
	TransactionsOut findByCode(String code) throws Exception;
	
	List<TransactionsOut> findAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
