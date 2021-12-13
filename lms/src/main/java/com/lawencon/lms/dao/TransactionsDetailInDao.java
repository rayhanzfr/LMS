package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.TransactionsDetailIn;

public interface TransactionsDetailInDao {
	List<TransactionsDetailIn> getAll() throws Exception;

	TransactionsDetailIn getById(String id) throws Exception;

	TransactionsDetailIn saveOrUpdate(TransactionsDetailIn transactionsDetailIn) throws Exception;
}
