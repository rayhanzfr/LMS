package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.service.PermissionsService;

public class PermissionsServiceImpl extends BaseServiceImpl implements PermissionsService {

	@Autowired
	private PermissionsDao permissionsDao;

	@Override
	public Permissions save(Permissions permissions) throws Exception {
		try {
			begin();
			permissions = permissionsDao.saveOrUpdate(permissions);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return permissions;
	}

	@Override
	public Permissions update(Permissions permissions) throws Exception {
		try {
			Permissions permissionsDb = findById(permissions.getId());	
			permissions.setCreatedAt(permissionsDb.getCreatedAt());
			permissions.setCreatedBy(permissionsDb.getCreatedBy());

			begin();
			permissions = permissionsDao.saveOrUpdate(permissions);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return permissions;
	}

	@Override
	public Permissions findById(String id) throws Exception {
		return permissionsDao.findById(id);
	}

	@Override
	public List<Permissions> findAll() throws Exception {
		return permissionsDao.findAll();
	}

	@Override
	public Permissions findByCode(String code) throws Exception {
		return permissionsDao.findByCode(code);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return permissionsDao.removeById(id);
	}
}
