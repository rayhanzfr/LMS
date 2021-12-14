package com.lawencon.lms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.model.Employees;

@Repository
public class EmployeesDaoImpl extends BaseDaoImpl<Employees> implements EmployeesDao{

	@Override
	public List<Employees> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Employees findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Employees saveOrUpdate(Employees employees) throws Exception {
		return save(employees);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return removeById(id);
	}
	
}
