package com.lawencon.lms.dao.impl;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.TransactionsInDao;
import com.lawencon.lms.model.TransactionsIn;

public class TransactionsInDaoImpl extends BaseDaoImpl<TransactionsIn> implements TransactionsInDao{

	@Override
	public List<TransactionsIn> findAll() throws Exception {
		return getAll();
	}

	@Override
	public TransactionsIn findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public TransactionsIn findByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionsIn saveOrUpdate(TransactionsIn transactionsIn) throws Exception {
		return save(transactionsIn);
	}

}
