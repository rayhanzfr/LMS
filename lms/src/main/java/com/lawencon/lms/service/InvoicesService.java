package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.invoices.SaveInvoicesResDto;
import com.lawencon.lms.dto.invoices.UpdateInvoicesResDto;
import com.lawencon.lms.model.Invoices;

public interface InvoicesService {

	List<Invoices> findAll() throws Exception;

	Invoices findById(String id) throws Exception;

	Invoices findByCode(String code) throws Exception;

	SaveInvoicesResDto save(Invoices invoices) throws Exception;
	
	UpdateInvoicesResDto update(Invoices invoices) throws Exception;

	Boolean removeById(String id) throws Exception;
}
