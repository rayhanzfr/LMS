package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.TransactionsIn;

public interface TransactionsInService {
	List<TransactionsIn> getAll() throws Exception;

	TransactionsIn getById(Long id) throws Exception;

	void saveOrUpdate(TransactionsIn transactionsIn) throws Exception;
}
