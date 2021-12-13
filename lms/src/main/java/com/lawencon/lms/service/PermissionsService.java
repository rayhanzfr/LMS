package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Permissions;

public interface PermissionsService {
	
	Permissions saveOrUpdate(Permissions permissions) throws Exception;
	
	Permissions getById(String id) throws Exception;
	
	Permissions getByCode(String code) throws Exception;
	
	List<Permissions> getAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}