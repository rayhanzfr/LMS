package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.HistoriesDao;
import com.lawencon.lms.model.Histories;
import com.lawencon.lms.service.HistoriesService;

public class HistoriesServiceImpl extends BaseServiceImpl implements HistoriesService {

	@Autowired
	private HistoriesDao historiesDao;

	@Override
	public Histories save(Histories histories) throws Exception {
		try {
			begin();
			histories = historiesDao.saveOrUpdate(histories);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return histories;
	}

	@Override
	public Histories findById(String id) throws Exception {
		return historiesDao.findById(id);
	}

	@Override
	public List<Histories> findAll() throws Exception {
		return historiesDao.findAll();
	}

	@Override
	public List<Histories> findByUsersId(String usersId) throws Exception {
		return historiesDao.findByUsersId(usersId);
	}

	
}
