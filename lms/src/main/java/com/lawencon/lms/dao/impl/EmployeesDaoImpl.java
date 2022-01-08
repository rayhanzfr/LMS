package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;

@Repository
public class EmployeesDaoImpl extends BaseDaoImpl<Employees> implements EmployeesDao {

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
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT e ");
		sql.append(" FROM Employees e ");
		sql.append(" INNER JOIN FETCH e.users u ");
		sql.append(" INNER JOIN FETCH u.roles ");
		sql.append(" INNER JOIN FETCH e.companies c ");
		sql.append(" INNER JOIN FETCH c.files f ");
		sql.append(" WHERE e.employeesCode = :code AND (f IS NULL OR f IS NOT NULL) ");
		
		Employees employees = createQuery(sql.toString(), Employees.class)
				.setParameter("code", code)
				.getSingleResult();
//		try {
//			sql.append(
//					" SELECT e.id, e.employees_code, c.companies_name ,u.users_email, e.employees_fullname, e.employees_address, e.employees_phone_number, e.created_by, e.created_at, e.updated_by, e.updated_at, e.version ");
//			sql.append(" FROM employees as e ");
//			sql.append(" INNER JOIN users as u ON  u.id = e.users_id ");
//			sql.append(" INNER JOIN companies as c ON  c.id = e.companies_id ");
//			sql.append(" WHERE e.employees_code = :code ");
//
//			Object resultQuery = createNativeQuery(sql.toString()).setParameter("code", code).getSingleResult();
//			if (resultQuery != null) {
//				Object[] obj = (Object[]) resultQuery;
//				employees = new Employees();
//				employees.setId(obj[0].toString());
//				employees.setEmployeesCode(obj[1].toString());
//
//				Companies companies = new Companies();
//				companies.setCompaniesName(obj[2].toString());
//
//				Users user = new Users();
//				user.setUsersEmail(obj[3].toString());
//				employees.setUsers(user);
//
//				employees.setEmployeesFullname(obj[4].toString());
//				employees.setEmployeesAddress(obj[5].toString());
//				employees.setEmployeesPhoneNumber(obj[6].toString());
//				employees.setCreatedBy(obj[7].toString());
//				employees.setCreatedAt(Timestamp.valueOf(obj[8].toString()).toLocalDateTime());
//
//				if (obj[9] != null) {
//					employees.setUpdatedBy(obj[9].toString());
//				}
//				if (obj[10] != null) {
//					employees.setUpdatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
//				}
//				employees.setVersion(Integer.valueOf(obj[11].toString()));
//			}
//		} catch (NoResultException e) {
//			e.printStackTrace();
//			throw new NoResultException("Not Found");
//		} catch (NonUniqueResultException e) {
//			e.printStackTrace();
//			throw new NoResultException("Found more than one");
//		}
		return employees;
	}

	@Override
	public Employees findByUserId(String id) throws Exception {
		Employees employees = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					" SELECT e.id, e.employees_code, c.companies_code, c.companies_name ,u.users_email, u.users_password, e.employees_fullname, e.employees_address, e.employees_phone_number, r.roles_code, r.roles_name ");
			sql.append(" FROM employees as e ");
			sql.append(" LEFT JOIN users as u ON  u.id = e.users_id ");
			sql.append(" LEFT JOIN companies as c ON  c.id = e.companies_id ");
			sql.append(" INNER JOIN roles as r ON  r.id = u.roles_id ");
			sql.append(" WHERE u.id = :id ");

			Object resultQuery = createNativeQuery(sql.toString()).setParameter("id", id).getSingleResult();
			if (resultQuery != null) {
				Object[] obj = (Object[]) resultQuery;
				employees = new Employees();
				employees.setId(obj[0].toString());
				employees.setEmployeesCode(obj[1].toString());

				Companies companies = new Companies();
				companies.setCompaniesCode(obj[2].toString());
				companies.setCompaniesName(obj[3].toString());
				employees.setCompanies(companies);

				Users user = new Users();
				user.setUsersEmail(obj[4].toString());
				user.setUsersPassword(obj[5].toString());
				

				employees.setEmployeesFullname(obj[6].toString());
				employees.setEmployeesAddress(obj[7].toString());
				employees.setEmployeesPhoneNumber(obj[8].toString());
				
				Roles roles = new Roles();
				roles.setRolesCode(obj[9].toString());
				roles.setRolesName(obj[10].toString());
				user.setRoles(roles);
				employees.setUsers(user);
			}else {
				return employees;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NoResultException("Found more than one");
		}
		return employees;
	}

	@Override
	public List<Employees> findByRoles(String rolesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT e.employees_fullname , u.users_email , r.roles_name  ");
		sql.append(" FROM employees e ");
		sql.append(" INNER JOIN users u ON u.id = e.users_id ");
		sql.append(" INNER JOIN roles r ON r.id =u.roles_id ");
		sql.append(" WHERE r.roles_code = :code ");

		List<?> result = createNativeQuery(sql.toString()).setParameter("code", rolesCode).getResultList();
		List<Employees> list = new ArrayList<Employees>();
		result.forEach(rs -> {
			Object[] obj = (Object[]) rs;
			Employees employee = new Employees();
			employee.setEmployeesFullname(obj[0].toString());

			Roles role = new Roles();
			role.setRolesName(obj[2].toString());

			Users user = new Users();
			user.setUsersEmail(obj[1].toString());
			user.setRoles(role);

			employee.setUsers(user);

			list.add(employee);
		});

		return list;
	}

	@Override
	public Employees findByCompaniesCode(String code) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT e ");
		sql.append(" FROM Employees e ");
		sql.append(" INNER JOIN FETCH e.users u ");
		sql.append(" INNER JOIN FETCH u.roles ");
		sql.append(" INNER JOIN FETCH e.companies c ");
		sql.append(" INNER JOIN FETCH c.files ");
		sql.append(" WHERE e.employeesCode = :code ");
		
		Employees employees = createQuery(sql.toString(), Employees.class)
				.setParameter("code", code)
				.getSingleResult();
		return null;
	}

	@Override
	public List<Employees> employeesCompany(String companiesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT e.id, e.employees_fullname,e.employees_phone_number, e.employees_address,e.employees_code,c.companies_code, c.companies_name, c.companies_address, c.companies_phone, u.users_email , r.roles_name  ");
		sql.append(" FROM employees e ");
		sql.append(" INNER JOIN users u ON u.id = e.users_id ");
		sql.append(" INNER JOIN roles r ON r.id =u.roles_id ");
		sql.append(" INNER JOIN companies c ON c.id =e.companies_id ");
		sql.append(" WHERE c.companies_code = :code ");

		List<?> result = createNativeQuery(sql.toString()).setParameter("code", companiesCode).getResultList();
		List<Employees> list = new ArrayList<Employees>();
		result.forEach(rs -> {
			Object[] obj = (Object[]) rs;
			Employees employee = new Employees();
			employee.setId(obj[0].toString());
			employee.setEmployeesFullname(obj[1].toString());
			employee.setEmployeesPhoneNumber(obj[2].toString());
			employee.setEmployeesAddress(obj[3].toString());
			employee.setEmployeesCode(obj[4].toString());
			
			Companies company = new Companies();
			company.setCompaniesCode(obj[5].toString());
			company.setCompaniesName(obj[6].toString());
			company.setCompaniesAddress(obj[7].toString());
			company.setCompaniesPhone(obj[8].toString());

			Roles role = new Roles();
			role.setRolesName(obj[10].toString());

			Users user = new Users();
			user.setUsersEmail(obj[9].toString());
			user.setRoles(role);

			employee.setUsers(user);

			list.add(employee);
		});

		return list;
	}

	@Override
	public Integer countData() throws Exception {
		String sql = "SELECT COUNT(e.id) FROM employees as e";
		Object result = createNativeQuery(sql).getSingleResult();
		Integer results = Integer.valueOf(result.toString());
		return results;
	}

}
