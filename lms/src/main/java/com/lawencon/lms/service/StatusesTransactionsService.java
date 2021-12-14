package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.StatusesTransactions;

public interface StatusesTransactionsService {
	StatusesTransactions saveOrUpdate(StatusesTransactions items) throws Exception;
	
	StatusesTransactions findById(String id) throws Exception;
	
	StatusesTransactions findByCode(String code) throws Exception;
	
	List<StatusesTransactions> findAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
