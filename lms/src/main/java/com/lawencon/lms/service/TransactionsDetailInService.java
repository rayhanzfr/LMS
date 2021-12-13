package com.lawencon.lms.service;

public interface TransactionsDetailInService {
	void getAll() throws Exception;

	void getById(Long id) throws Exception;

	TransactionsDetailInService insert(TransactionsDetailInService transactionsInService) throws Exception;

	TransactionsDetailInService update(TransactionsDetailInService transactionsInService) throws Exception;
}
