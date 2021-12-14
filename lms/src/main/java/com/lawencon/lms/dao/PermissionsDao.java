package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Permissions;

public interface PermissionsDao {
	
	Permissions saveOrUpdate(Permissions permissions) throws Exception;
	
	Permissions findById(String id) throws Exception;
	
	Permissions findByCode(String code) throws Exception;
	
	List<Permissions> findAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
