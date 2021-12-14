package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Employees;

public interface EmployeesService {

	List<Employees> findAll()throws Exception;
	Employees findById()throws Exception;
	void saveOrUpdate(Employees employees) throws Exception;
	Boolean deleteById()throws Exception;
}
