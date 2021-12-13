package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.TransactionsIn;

public interface TransactionsInDao {
	List<TransactionsIn> getAll() throws Exception;

	TransactionsIn getById(String id) throws Exception;

	TransactionsIn getByCode(String code) throws Exception;
	
	TransactionsIn saveOrUpdate(TransactionsIn transactionsIn) throws Exception;
}
