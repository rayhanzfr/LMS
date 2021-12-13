package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.PermissionsRoles;

public interface PermissionsRolesDao {
	
	void saveOrUpdate(PermissionsRoles permissionsRoles) throws Exception;
	
	PermissionsRoles getById(String id) throws Exception;
	
	List<PermissionsRoles> getAll() throws Exception;
}
