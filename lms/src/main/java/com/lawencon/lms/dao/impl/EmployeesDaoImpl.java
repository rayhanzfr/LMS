package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Users;

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
		return deleteById(id);
	}

	@Override
	public Employees findByCode(String code) throws Exception {
		Employees employees=null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT e.id, e.code, c.companies_name ,u.users_email, e.employees_fullname, e.employees_address, e.employees_phone_number, e.craeted_by, e.created_at, e.updated_by, e.updated_at, e.version ");
			sql.append(" FROM employees as e ");
			sql.append(" INNER JOIN users as u ON  u.id = e.users_id ");
			sql.append(" INNER JOIN companies as c ON  c.id = e.companies_id ");
			sql.append(" WHERE e.employees_code = :code ");
			
			Object resultQuery = createNativeQuery(sql.toString())
					.setParameter("code", code)
					.getSingleResult();
			if(resultQuery!=null) {
				Object[] obj = (Object[]) resultQuery;
				employees = new Employees();
				employees.setId(obj[0].toString());
				employees.setEmployeesCode(obj[1].toString());
				
				Companies companies = new Companies();
				companies.setCompaniesName(obj[2].toString());
				
				Users user = new Users();
				user.setUsersEmail(obj[3].toString());
				employees.setUsers(user);
				
				employees.setEmployeesFullname(obj[4].toString());
				employees.setEmployeesAddress(obj[5].toString());
				employees.setEmployeesPhoneNumber(obj[6].toString());
				employees.setCreatedBy(obj[7].toString());
				employees.setCreatedAt(Timestamp.valueOf(obj[8].toString()).toLocalDateTime());
				
				if(obj[9]!=null) {
					employees.setUpdatedBy(obj[9].toString());
				}
				if(obj[10]!=null) {
					employees.setUpdatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
				}
				employees.setVersion(Integer.valueOf(obj[11].toString()));
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
}
