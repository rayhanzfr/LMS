package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Employees;

public interface EmployeesService {

	List<Employees> getAll()throws Exception;
	Employees getById()throws Exception;
	void saveOrUpdate(Employees employees) throws Exception;
	Boolean deleteById()throws Exception;
}
