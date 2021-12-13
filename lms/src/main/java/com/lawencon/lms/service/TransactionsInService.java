package com.lawencon.lms.service;

public interface TransactionsInService {
	void getAll() throws Exception;

	void getById(Long id) throws Exception;

	TransactionsInService insert(TransactionsInService transactionsInService) throws Exception;

	TransactionsInService update(TransactionsInService transactionsInService) throws Exception;
}
