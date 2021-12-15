package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.statusestransactions.SaveStatusesTransactionsResDto;
import com.lawencon.lms.dto.statusestransactions.UpdateStatusesTransactionsResDto;
import com.lawencon.lms.model.StatusesTransactions;

public interface StatusesTransactionsService {
	SaveStatusesTransactionsResDto save(StatusesTransactions items) throws Exception;
	
	UpdateStatusesTransactionsResDto update(StatusesTransactions items) throws Exception;
	
	StatusesTransactions findById(String id) throws Exception;
	
	StatusesTransactions findByCode(String code) throws Exception;
	
	List<StatusesTransactions> findAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
