package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.TransactionsDetailIn;

public interface TransactionsDetailInService {
	List<TransactionsDetailIn> findAll() throws Exception;

	TransactionsDetailIn findById(String id) throws Exception;

	void save(TransactionsDetailIn transactionsDetailIn) throws Exception;
	
	void update(TransactionsDetailIn transactionsDetailIn) throws Exception;
}
