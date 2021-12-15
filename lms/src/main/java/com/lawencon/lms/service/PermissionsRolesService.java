package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.permissionsroles.SavePermissionsRolesResDto;
import com.lawencon.lms.dto.permissionsroles.UpdatePermissionsRolesResDto;
import com.lawencon.lms.model.PermissionsRoles;

public interface PermissionsRolesService {
	SavePermissionsRolesResDto save(PermissionsRoles permissionsRoles) throws Exception;
	
	UpdatePermissionsRolesResDto update(PermissionsRoles permissionsRoles) throws Exception;
	
	PermissionsRoles findById(String id) throws Exception;
	
	List<PermissionsRoles> findAll() throws Exception;

	Boolean removeById(String id) throws Exception;
}
