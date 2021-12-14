package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.PermissionsRoles;

public interface PermissionsRolesService {
	PermissionsRoles saveOrUpdate(PermissionsRoles permissionsRoles) throws Exception;
	
	PermissionsRoles findById(String id) throws Exception;
	
	List<PermissionsRoles> findAll() throws Exception;

	Boolean removeById(String id) throws Exception;
}
