package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.service.RolesService;

public class RolesServiceImpl extends BaseServiceImpl implements RolesService {

	@Autowired
	private RolesDao rolesDao;

	@Override
	public Roles save(Roles roles) throws Exception {
		try {
			begin();
			rolesDao.saveOrUpdate(roles);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return null;
	}

	@Override
	public Roles update(Roles roles) throws Exception {
		try {
			Roles rolesDb = findById(roles.getId());	
			roles.setCreatedAt(rolesDb.getCreatedAt());
			roles.setCreatedBy(rolesDb.getCreatedBy());

			begin();
			rolesDao.saveOrUpdate(roles);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return null;
	}

	@Override
	public Roles findById(String id) throws Exception {
		return rolesDao.findById(id);
	}

	@Override
	public List<Roles> findAll() throws Exception {
		return rolesDao.findAll();
	}

	@Override
	public Roles findByCode(String code) throws Exception {
		return rolesDao.findByCode(code);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return rolesDao.removeById(id);
	}
}
