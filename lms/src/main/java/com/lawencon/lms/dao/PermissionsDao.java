package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Permissions;

public interface PermissionsDao {
	
	void saveOrUpdate(Permissions permissions) throws Exception;
	
	Permissions getById(String id) throws Exception;
	
	Permissions getByCode(String code) throws Exception;
	
	List<Permissions> getAll() throws Exception;
}
