package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.TransactionsDetailOut;

public interface TransactionsDetailOutDao {
	TransactionsDetailOut findById(String id) throws Exception;
	
	List<TransactionsDetailOut> findByTransactionOutCode(String tansactionOutCode) throws Exception;
	
	TransactionsDetailOut saveOrUpdate(TransactionsDetailOut transactionsDetailOut) throws Exception;
}
