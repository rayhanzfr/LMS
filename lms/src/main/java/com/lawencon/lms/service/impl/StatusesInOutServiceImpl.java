package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.StatusesInOutDao;
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.service.StatusesInOutService;

public class StatusesInOutServiceImpl extends BaseServiceImpl implements StatusesInOutService {

	@Autowired
	private StatusesInOutDao statusesInOutDao;
	
	
	@Override
	public List<StatusesInOut> findAll() throws Exception {
		return statusesInOutDao.findAll();
	}

	@Override
	public StatusesInOut findById(String id) throws Exception {
		return statusesInOutDao.findById(id);
	}

	@Override
	public StatusesInOut findByCode(String code) throws Exception {
		return statusesInOutDao.findByCode(code);
	}

	@Override
	public StatusesInOut save(StatusesInOut statusesInOut) throws Exception {
		try {
			begin();
			statusesInOut = statusesInOutDao.saveOrUpdate(statusesInOut);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return statusesInOut;
	}

	@Override
	public StatusesInOut update(StatusesInOut statusesInOut) throws Exception {
		try {
			StatusesInOut statusesInAndOut = statusesInOutDao.findByCode(statusesInOut.getStatusesInOutCode());
			statusesInOut.setCreatedBy(statusesInAndOut.getCreatedBy());
			statusesInOut.setCreatedAt(statusesInAndOut.getCreatedAt());
			
			begin();
			statusesInOut = statusesInOutDao.saveOrUpdate(statusesInOut);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return statusesInOut;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return removeById(id);
	}

}
