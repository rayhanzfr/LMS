package com.lawencon.lms.dao.impl;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.CompaniesDao;
import com.lawencon.lms.model.Companies;

public class CompaniesDaoImpl extends BaseDaoImpl<Companies> implements CompaniesDao{

	@Override
	public List<Companies> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Companies findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Companies findByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Companies saveOrUpdate(Companies companies) throws Exception {
		return save(companies);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
