package com.lawencon.lms.dao.impl;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.TransactionsDetailInDao;
import com.lawencon.lms.model.TransactionsDetailIn;

public class TransactionsDetailInDaoImpl extends BaseDaoImpl<TransactionsDetailIn> implements TransactionsDetailInDao{

	@Override
	public List<TransactionsDetailIn> findAll() throws Exception {
		return getAll();
	}

	@Override
	public TransactionsDetailIn findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public TransactionsDetailIn saveOrUpdate(TransactionsDetailIn transactionsIn) throws Exception {
		return save(transactionsIn);
	}

}
