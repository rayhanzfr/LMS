package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Invoices;

public interface InvoicesService {

	List<Invoices> findAll() throws Exception;

	Invoices findById(String id) throws Exception;

	Invoices findByCode(String id) throws Exception;

	Invoices save(Invoices invoices) throws Exception;
	
	Invoices update(Invoices invoices) throws Exception;

	Boolean removeById(String id) throws Exception;
}
