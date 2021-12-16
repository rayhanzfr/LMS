package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.TransactionsIn;

public interface TransactionsInDao {
	List<TransactionsIn> findAll() throws Exception;

	TransactionsIn findById(String id) throws Exception;

	TransactionsIn findByCode(String code) throws Exception;
	
	TransactionsIn saveOrUpdate(TransactionsIn transactionsIn) throws Exception;
	
	String countData() throws Exception;
}
