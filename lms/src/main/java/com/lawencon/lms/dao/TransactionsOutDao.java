package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.TransactionsOut;

public interface TransactionsOutDao {
	TransactionsOut saveOrUpdate(TransactionsOut items) throws Exception;
	
	TransactionsOut getById(String id) throws Exception;
	
	TransactionsOut getByCode(String code) throws Exception;
	
	List<TransactionsOut> getAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
