package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Invoices;

public interface InvoicesService {
	List<Invoices> getAll() throws Exception;

	Invoices getById(Long id) throws Exception;

	void saveOrUpdate(Invoices invoices) throws Exception;
	
	Boolean deleteById(Long id) throws Exception;
}
