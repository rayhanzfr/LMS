package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.PermissionsRoles;

public interface PermissionsRolesService {
	PermissionsRoles saveOrUpdate(PermissionsRoles permissionsRoles) throws Exception;
	
	PermissionsRoles getById(String id) throws Exception;
	
	List<PermissionsRoles> getAll() throws Exception;

	Boolean removeById(String id) throws Exception;
}
