package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.TransactionsOutDao;
import com.lawencon.lms.model.TransactionsOut;
import com.lawencon.lms.service.TransactionsOutService;

public class TransactionsOutServiceImpl extends BaseServiceImpl implements TransactionsOutService {

	@Autowired
	private TransactionsOutDao transactionsOutDao;

	@Override
	public TransactionsOut save(TransactionsOut transactionsOut) throws Exception {
		try {
			begin();
			transactionsOut=transactionsOutDao.saveOrUpdate(transactionsOut);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return transactionsOut;
	}

	@Override
	public TransactionsOut update(TransactionsOut transactionsOut) throws Exception {
		try {
			TransactionsOut transactionsOutDb = findByCode(transactionsOut.getTransactionsOutCode());	
			transactionsOut.setCreatedAt(transactionsOutDb.getCreatedAt());
			transactionsOut.setCreatedBy(transactionsOutDb.getCreatedBy());

			begin();
			transactionsOut=transactionsOutDao.saveOrUpdate(transactionsOut);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return transactionsOut;
	}

	@Override
	public TransactionsOut findById(String id) throws Exception {
		return transactionsOutDao.findById(id);
	}

	@Override
	public List<TransactionsOut> findAll() throws Exception {
		return transactionsOutDao.findAll();
	}

	@Override
	public TransactionsOut findByCode(String code) throws Exception {
		return transactionsOutDao.findByCode(code);
	}

}
