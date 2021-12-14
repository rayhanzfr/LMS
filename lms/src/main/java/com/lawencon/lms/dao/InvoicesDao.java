package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Invoices;

public interface InvoicesDao {
	List<Invoices> findAll() throws Exception;

	Invoices findById(String id) throws Exception;

	Invoices findByCode(String id) throws Exception;
	
	Invoices saveOrUpdate(Invoices invoices) throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
