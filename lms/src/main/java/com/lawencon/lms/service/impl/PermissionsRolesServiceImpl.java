package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.service.PermissionsRolesService;
import com.lawencon.lms.service.PermissionsService;
import com.lawencon.lms.service.RolesService;

public class PermissionsRolesServiceImpl extends BaseServiceImpl implements PermissionsRolesService {

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Autowired
	private PermissionsService permissionsService;

	@Autowired
	private RolesService rolesService;

	@Override
	public PermissionsRoles save(PermissionsRoles permissionsRoles) throws Exception {
		try {
			Permissions permissions = permissionsService.findByCode(permissionsRoles.getPermissions().getPermissionsCode());
			Roles roles = rolesService.findByCode(permissionsRoles.getRoles().getRolesCode());
			permissionsRoles.setPermissions(permissions);
			permissionsRoles.setRoles(roles);
			begin();
			permissionsRoles = permissionsRolesDao.saveOrUpdate(permissionsRoles);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return permissionsRoles;
	}

	@Override
	public PermissionsRoles update(PermissionsRoles permissionsRoles) throws Exception {
		try {
			Permissions permissions = permissionsService.findByCode(permissionsRoles.getPermissions().getPermissionsCode());
			Roles roles = rolesService.findByCode(permissionsRoles.getRoles().getRolesCode());
			permissionsRoles.setPermissions(permissions);
			permissionsRoles.setRoles(roles);
			PermissionsRoles permissionsRolesDb = findById(permissionsRoles.getId());
			permissionsRoles.setCreatedAt(permissionsRolesDb.getCreatedAt());
			permissionsRoles.setCreatedBy(permissionsRolesDb.getCreatedBy());
			begin();
			permissionsRoles = permissionsRolesDao.saveOrUpdate(permissionsRoles);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return permissionsRoles;
	}

	@Override
	public PermissionsRoles findById(String id) throws Exception {
		return permissionsRolesDao.findById(id);
	}

	@Override
	public List<PermissionsRoles> findAll() throws Exception {
		return permissionsRolesDao.findAll();
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return permissionsRolesDao.removeById(id);
	}
}
