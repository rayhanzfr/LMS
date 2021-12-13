package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Invoices;

public interface InvoicesDao {
	List<Invoices> getAll() throws Exception;

	Invoices getById(String id) throws Exception;

	Invoices getByCode(String id) throws Exception;
	
	Invoices saveOrUpdate(Invoices invoices) throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
