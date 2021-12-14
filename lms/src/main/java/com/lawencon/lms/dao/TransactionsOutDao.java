package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.TransactionsOut;

public interface TransactionsOutDao {
	TransactionsOut saveOrUpdate(TransactionsOut items) throws Exception;
	
	TransactionsOut findById(String id) throws Exception;
	
	TransactionsOut findByCode(String code) throws Exception;
	
	List<TransactionsOut> findAll() throws Exception;
}
