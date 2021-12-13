package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.StatusesTransactions;

public interface StatusesTransactionsService {
	StatusesTransactions saveOrUpdate(StatusesTransactions items) throws Exception;
	
	StatusesTransactions getById(String id) throws Exception;
	
	StatusesTransactions getByCode(String code) throws Exception;
	
	List<StatusesTransactions> getAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
