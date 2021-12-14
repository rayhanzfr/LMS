package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.TransactionsDetailIn;

public interface TransactionsDetailInService {
	List<TransactionsDetailIn> findAll() throws Exception;

	TransactionsDetailIn findById(Long id) throws Exception;

	void saveOrUpdate(TransactionsDetailIn transactionsDetailIn) throws Exception;
}
