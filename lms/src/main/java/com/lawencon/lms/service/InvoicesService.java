package com.lawencon.lms.service;

import com.lawencon.lms.model.Invoices;

public interface InvoicesService {
	void getAll() throws Exception;

	void getById(Long id) throws Exception;

	Invoices insert(Invoices invoices) throws Exception;

	Invoices update(Invoices invoices) throws Exception;

	void deleteById(Long id) throws Exception;
}
