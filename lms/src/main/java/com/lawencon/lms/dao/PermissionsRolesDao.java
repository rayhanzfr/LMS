package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.PermissionsRoles;

public interface PermissionsRolesDao {
	
	PermissionsRoles saveOrUpdate(PermissionsRoles permissionsRoles) throws Exception;
	
	PermissionsRoles findById(String id) throws Exception;
	
	List<PermissionsRoles> findAll() throws Exception;

	Boolean removeById(String id) throws Exception;
}
