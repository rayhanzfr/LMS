package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.service.StatusesAssetsService;

public class StatusesAssetsServiceImpl extends BaseServiceImpl implements StatusesAssetsService {

	@Autowired
	private StatusesAssetsDao statusesAssetsDao;

	@Override
	public List<StatusesAssets> findAll() throws Exception {
		return statusesAssetsDao.findAll();
	}

	@Override
	public StatusesAssets findById(String id) throws Exception {
		return statusesAssetsDao.findById(id);
	}

	@Override
	public StatusesAssets findByCode(String code) throws Exception {
		return statusesAssetsDao.findByCode(code);
	}

	@Override
	public StatusesAssets save(StatusesAssets statusesAssets) throws Exception {
		try {
			begin();
			statusesAssets = statusesAssetsDao.saveOrUpdate(statusesAssets);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return statusesAssets;
	}

	@Override
	public StatusesAssets update(StatusesAssets statusesAssets) throws Exception {
		try {
			StatusesAssets statusesAssetsDb = findById(statusesAssets.getId());	
			statusesAssets.setCreatedAt(statusesAssetsDb.getCreatedAt());
			statusesAssets.setCreatedBy(statusesAssetsDb.getCreatedBy());

			begin();
			statusesAssets = statusesAssetsDao.saveOrUpdate(statusesAssets);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return statusesAssets;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return statusesAssetsDao.removeById(id);
	}

}
