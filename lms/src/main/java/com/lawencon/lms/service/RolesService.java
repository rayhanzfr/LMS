package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Roles;

public interface RolesService {
	Roles saveOrUpdate(Roles request) throws Exception;

	Roles findById(String id) throws Exception;

	Roles findByCode(String code) throws Exception;

	List<Roles> findAll() throws Exception;
	
	Boolean removeById (String id) throws Exception;

}
