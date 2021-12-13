package com.lawencon.lms.dao;

import java.util.List;

public interface StatusesTransactionsDao {
	void saveOrUpdate(StatusesTransactionsDao items) throws Exception;
	
	StatusesTransactionsDao getById(String id) throws Exception;
	
	StatusesTransactionsDao getByCode(String code) throws Exception;
	
	List<StatusesTransactionsDao> getAll() throws Exception;
}
