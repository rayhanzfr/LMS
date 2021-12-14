package com.lawencon.lms.dao.impl;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.model.StatusesAssets;

public class StatusesAssetsDaoImpl extends BaseDaoImpl<StatusesAssets> implements StatusesAssetsDao{

	@Override
	public List<StatusesAssets> findAll() throws Exception {
		return getAll();
	}

	@Override
	public StatusesAssets findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public StatusesAssets findByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatusesAssets saveOrUpdate(StatusesAssets companies) throws Exception {
		return save(companies);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
