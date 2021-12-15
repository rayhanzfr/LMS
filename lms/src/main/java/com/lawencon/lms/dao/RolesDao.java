package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Roles;

public interface RolesDao {

	Roles saveOrUpdate(Roles roles) throws Exception;
	
	Roles findById(String id) throws Exception;
	
	Roles findByCode(String code) throws Exception;
	
	List<Roles> findAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
	
	Integer countData() throws Exception;
}
