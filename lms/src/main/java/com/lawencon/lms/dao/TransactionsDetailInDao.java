package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.TransactionsDetailIn;

public interface TransactionsDetailInDao {
	List<TransactionsDetailIn> findAll() throws Exception;

	TransactionsDetailIn findById(String id) throws Exception;

	List<TransactionsDetailIn> findByTransactionInCode(String code) throws Exception;
	
	TransactionsDetailIn saveOrUpdate(TransactionsDetailIn transactionsDetailIn) throws Exception;
}
