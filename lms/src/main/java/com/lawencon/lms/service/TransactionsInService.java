package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.TransactionsIn;

public interface TransactionsInService {

	List<TransactionsIn> findAll() throws Exception;

	TransactionsIn findById(String id) throws Exception;

	TransactionsIn findByCode(String code) throws Exception;

	TransactionsIn save(TransactionsIn transactionsIn) throws Exception;

	TransactionsIn update(TransactionsIn transactionsIn) throws Exception;
}
