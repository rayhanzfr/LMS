package com.lawencon.lms.dao.impl;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.LocationsDao;
import com.lawencon.lms.model.Locations;

public class LocationsDaoImpl extends BaseDaoImpl<Locations> implements LocationsDao{

	@Override
	public List<Locations> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Locations findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Locations findByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locations saveOrUpdate(Locations locations) throws Exception {
		return save(locations);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
