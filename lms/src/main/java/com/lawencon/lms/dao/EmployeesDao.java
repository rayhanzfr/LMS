package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Employees;

public interface EmployeesDao {

	List<Employees> findAll() throws Exception;

	Employees findById(String id) throws Exception;
	
	Employees findByUserId (String id) throws Exception;

	Employees findByCode(String Code) throws Exception;

	Employees saveOrUpdate(Employees employees) throws Exception;
	
	List<Employees>findByRoles(String rolesCode)throws Exception;

	Boolean removeById(String id) throws Exception;
}
