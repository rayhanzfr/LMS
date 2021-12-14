package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.TransactionsInDao;
import com.lawencon.lms.model.TransactionsIn;
import com.lawencon.lms.service.TransactionsInService;

public class TransactionsInServiceImpl extends BaseServiceImpl implements TransactionsInService {

	@Autowired
	private TransactionsInDao transactionsInDao;

	@Override
	public List<TransactionsIn> findAll() throws Exception {
		return transactionsInDao.findAll();
	}

	@Override
	public TransactionsIn findById(String id) throws Exception {
		return transactionsInDao.findById(id);
	}

	@Override
	public TransactionsIn findByCode(String code) throws Exception {
		return transactionsInDao.findByCode(code);
	}

	@Override
	public TransactionsIn save(TransactionsIn transactionsIn) throws Exception {
		try {
			begin();
			transactionsIn = transactionsInDao.saveOrUpdate(transactionsIn);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return transactionsIn;
	}

	@Override
	public TransactionsIn update(TransactionsIn transactionsIn) throws Exception {
		try {
			TransactionsIn transactionsOutDb = findByCode(transactionsIn.getTransactionsInCode());
			transactionsIn.setCreatedAt(transactionsOutDb.getCreatedAt());
			transactionsIn.setCreatedBy(transactionsOutDb.getCreatedBy());

			begin();
			transactionsIn = transactionsInDao.saveOrUpdate(transactionsIn);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return transactionsIn;
	}

}
