package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.dto.employees.SaveEmployeesResDto;
import com.lawencon.lms.dto.employees.UpdateEmployeesResDto;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.CompaniesService;
import com.lawencon.lms.service.EmployeesService;
import com.lawencon.lms.service.UsersService;

public class EmployeesServiceImpl extends BaseServiceImpl implements EmployeesService{

	@Autowired
	private EmployeesDao employeesDao;
	
	@Autowired
	private UsersService usersService;

	@Autowired
	private CompaniesService companiesService;
	
	
	@Override
	public List<Employees> findAll() throws Exception {
		return employeesDao.findAll();
	}


	@Override
	public Employees findById(String id) throws Exception {
		return employeesDao.findById(id);
	}


	@Override
	public SaveEmployeesResDto save(Employees employees) throws Exception {
		SaveEmployeesResDto resDto = new SaveEmployeesResDto();
		try {
			Users user = usersService.findByEmail(employees.getUsers().getUsersEmail());
			employees.setUsers(user);
			
			begin();
			employees = employeesDao.saveOrUpdate(employees);
			commit();
			resDto.setId(employees.getId());
			resDto.setMessage("INSERTED");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return resDto;
	}


	@Override
	public UpdateEmployeesResDto update(Employees employees) throws Exception {
		UpdateEmployeesResDto resDto = new UpdateEmployeesResDto();
		try {
			Users user = usersService.findByEmail(employees.getUsers().getUsersEmail());
			employees.setUsers(user);
			
			Companies companies = companiesService.findByCode(employees.getCompanies().getCompaniesCode());
			employees.setCompanies(companies);
			
			Employees employee = employeesDao.findByCode(employees.getEmployeesCode());
			employees.setCreatedAt(employee.getCreatedAt());
			employees.setCreatedBy(employee.getCreatedBy());
			
			begin();
			employees = employeesDao.saveOrUpdate(employees);
			commit();
			
			resDto.setVersion(employees.getVersion());
			resDto.setMessage("UPDATED");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return resDto;
	}


	@Override
	public Boolean removeById(String id) throws Exception {
		try {
			begin();
			boolean delete = employeesDao.removeById(id);
			commit();
			
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}


	@Override
	public Employees findByCode(String code) throws Exception {
		return employeesDao.findByCode(code);
	}
	
}
