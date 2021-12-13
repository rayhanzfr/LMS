package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Roles;

public interface RolesDao {

	Roles saveOrUpdate(Roles roles) throws Exception;
	
	Roles getById(String id) throws Exception;
	
	Roles getByCode(String code) throws Exception;
	
	List<Roles> getAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
