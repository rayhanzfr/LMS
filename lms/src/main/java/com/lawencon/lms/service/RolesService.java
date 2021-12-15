package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.roles.SaveRolesResDto;
import com.lawencon.lms.dto.roles.UpdateRolesResDto;
import com.lawencon.lms.model.Roles;

public interface RolesService {
	SaveRolesResDto save(Roles roles) throws Exception;
	
	UpdateRolesResDto update(Roles roles) throws Exception;

	Roles findById(String id) throws Exception;

	Roles findByCode(String code) throws Exception;

	List<Roles> findAll() throws Exception;
	
	Boolean removeById (String id) throws Exception;

}
