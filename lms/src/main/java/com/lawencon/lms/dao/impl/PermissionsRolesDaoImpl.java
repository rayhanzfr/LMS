package com.lawencon.lms.dao.impl;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.model.PermissionsRoles;

public class PermissionsRolesDaoImpl extends BaseDaoImpl<PermissionsRoles> implements PermissionsRolesDao {

	@Override
	public PermissionsRoles findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<PermissionsRoles> findAll() throws Exception {
		return getAll();
	}

	@Override
	public PermissionsRoles saveOrUpdate(PermissionsRoles permissionsRoles) throws Exception {
		return save(permissionsRoles);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}
