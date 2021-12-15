package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.employees.SaveEmployeesResDto;
import com.lawencon.lms.dto.employees.UpdateEmployeesResDto;
import com.lawencon.lms.model.Employees;

public interface EmployeesService {

	List<Employees> findAll()throws Exception;
	Employees findById(String id)throws Exception;
	Employees findByCode(String code)throws Exception;
	SaveEmployeesResDto save(Employees employees) throws Exception;
	UpdateEmployeesResDto update(Employees employees) throws Exception;
	Boolean removeById(String id)throws Exception;
}
