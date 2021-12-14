package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.StatusesTransactionsDao;
import com.lawencon.lms.model.StatusesTransactions;
import com.lawencon.lms.service.StatusesTransactionsService;

public class StatusesTransactionsServiceImpl extends BaseServiceImpl implements StatusesTransactionsService {

	@Autowired
	private StatusesTransactionsDao statusesTransactionsDao;

	@Override
	public StatusesTransactions save(StatusesTransactions statusesTransactions) throws Exception {
		try {
			begin();
			statusesTransactions = statusesTransactionsDao.saveOrUpdate(statusesTransactions);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return statusesTransactions;
	}

	@Override
	public StatusesTransactions update(StatusesTransactions statusesTransactions) throws Exception {
		try {
			StatusesTransactions statusesTransactionsDb = findByCode(statusesTransactions.getStatusesTransactionsCode());	
			statusesTransactions.setCreatedAt(statusesTransactionsDb.getCreatedAt());
			statusesTransactions.setCreatedBy(statusesTransactionsDb.getCreatedBy());

			begin();
			statusesTransactions = statusesTransactionsDao.saveOrUpdate(statusesTransactions);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return statusesTransactions;
	}

	@Override
	public StatusesTransactions findById(String id) throws Exception {
		return statusesTransactionsDao.findById(id);
	}

	@Override
	public List<StatusesTransactions> findAll() throws Exception {
		return statusesTransactionsDao.findAll();
	}

	@Override
	public StatusesTransactions findByCode(String code) throws Exception {
		return statusesTransactionsDao.findByCode(code);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return statusesTransactionsDao.removeById(id);
	}
}
