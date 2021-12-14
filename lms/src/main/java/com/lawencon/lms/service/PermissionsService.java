package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Permissions;

public interface PermissionsService {
	
	Permissions save(Permissions permissions) throws Exception;
	
	Permissions update(Permissions permissions) throws Exception;

	Permissions findById(String id) throws Exception;

	Permissions findByCode(String code) throws Exception;

	List<Permissions> findAll() throws Exception;
	
	Boolean removeById (String id) throws Exception;
}
