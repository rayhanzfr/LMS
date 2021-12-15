package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Employees;

public interface EmployeesService {

	List<Employees> findAll()throws Exception;
	Employees findById(String id)throws Exception;
	Employees save(Employees employees) throws Exception;
	Employees update(Employees employees) throws Exception;
	Boolean removeById(String id)throws Exception;
}
