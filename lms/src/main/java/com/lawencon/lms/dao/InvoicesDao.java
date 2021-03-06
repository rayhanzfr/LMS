package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Invoices;

public interface InvoicesDao {
	List<Invoices> findAll() throws Exception;
	
	List<Invoices> findByCompanies(String companiesCode) throws Exception;

	Invoices findById(String id) throws Exception;

	Invoices findByCode(String id) throws Exception;
	
	Invoices saveOrUpdate(Invoices invoices) throws Exception;
	
	Integer countData() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
