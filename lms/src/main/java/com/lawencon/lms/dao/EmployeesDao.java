package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Employees;

public interface EmployeesDao {

	List<Employees> getAll()throws Exception;
	Employees getById(String id)throws Exception;
	Employees saveOrUpdate(Employees employees) throws Exception;
	Boolean removeById(String id)throws Exception;
}
