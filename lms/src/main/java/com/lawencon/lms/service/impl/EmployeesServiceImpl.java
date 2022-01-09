package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.CompaniesDao;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.employees.SaveEmployeesResDto;
import com.lawencon.lms.dto.employees.UpdateEmployeesResDto;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.CompaniesService;
import com.lawencon.lms.service.EmployeesService;
import com.lawencon.lms.service.UsersService;

@Service
public class EmployeesServiceImpl extends BaseServiceLmsImpl implements EmployeesService{

	@Autowired
	private EmployeesDao employeesDao;

	@Autowired
	private CompaniesDao companiesDao;
	
	@Autowired
	private RolesDao rolesDao;
	
	@Autowired
	private PermissionsDao permissionsDao;
	
	@Autowired
	private PermissionsRolesDao permissionRolesDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Override
	public List<Employees> findAll() throws Exception {
		String permissionCode = "PERMSN17";
		boolean validation = validation(permissionCode);
		if(validation) {
		return employeesDao.findAll();
		}
		throw new Exception("Access Denied");
	}

	public String companiesCode()throws Exception{
		Employees employees = employeesDao.findByUserId(getIdAuth());
		String companiesCode = employees.getCompanies().getCompaniesCode();
		return companiesCode;
	}

	@Override
	public Employees findById(String id) throws Exception {
		String permissionCode = "PERMSN17";
		boolean validation = validation(permissionCode);
		if(validation)
		return employeesDao.findById(id);
		throw new Exception("Access Denied");
	}


	@Override
	public SaveEmployeesResDto save(Employees employees) throws Exception {
		SaveEmployeesResDto resDto = new SaveEmployeesResDto();
		String permissionCode = "PERMSN18";
		boolean validation = validation(permissionCode);
		if(validation) {
					begin();
					Users user = usersDao.findByEmail(employees.getUsers().getUsersEmail());
					employees.setUsers(user);
					Companies company = companiesDao.findByCode(employees.getCompanies().getCompaniesCode());
					employees.setCompanies(company);
					employees.setEmployeesCode(generateCode());
					employees.setCreatedBy(getIdAuth());
					employees = employeesDao.saveOrUpdate(employees);
					resDto.setId(employees.getId());
					resDto.setMessage("You was creating new Employee");	
					commit();

			return resDto;
		}
		else {
			throw new Exception("Access Denied");
		}
	}


	@Override
	public UpdateEmployeesResDto update(Employees employees) throws Exception {
		UpdateEmployeesResDto resDto = new UpdateEmployeesResDto();
		String permissionCode = "PERMSN19";
		boolean validation = validation(permissionCode);
		if(validation) {
			try {
				Users user = usersDao.findByEmail(employees.getUsers().getUsersEmail());
				
				Companies companies = companiesDao.findByCode(employees.getCompanies().getCompaniesCode());
				
				Employees employee = employeesDao.findByCode(employees.getEmployeesCode());
				employee.setUsers(user);
				employee.setCompanies(companies);
				employee.setEmployeesFullname(employees.getEmployeesFullname());
				employee.setEmployeesAddress(employees.getEmployeesAddress());
				employee.setUpdatedBy(getIdAuth());
				
				begin();
				employees = employeesDao.saveOrUpdate(employee);
				commit();
				
				resDto.setVersion(employees.getVersion());
				resDto.setMessage("Success updating employee "+employees.getEmployeesFullname());
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return resDto;
		}
		else {
			throw new Exception("Access Denied");
		}
	}


	@Override
	public Boolean removeById(String id) throws Exception {
		String permissionCode = "PERMSN20";
		boolean validation = validation(permissionCode);
		if(validation) {
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
		else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public Employees findByCode(String code) throws Exception {
		String permissionCode = "PERMSN17";
		boolean validation = validation(permissionCode);
		if(validation) {
			return employeesDao.findByCode(code);
		}
		else {
			throw new Exception("Access Denied");
		}
	}
	
	public boolean validation(String permissionsCode)throws Exception{
		try {
			boolean check = false;
			Users users = usersDao.findById(getIdAuth());
			Roles roles = rolesDao.findById(users.getRoles().getId());
			Permissions permissions = permissionsDao.findByCode(permissionsCode);
			List<PermissionsRoles> listPermissionsRoles = permissionRolesDao.findAll();
			for (int i = 0; i < listPermissionsRoles.size(); i++) {
				if (listPermissionsRoles.get(i).getPermissions().getId().equals(permissions.getId())) {
					if (listPermissionsRoles.get(i).getRoles().getId().equals(roles.getId())) {
						check = true;
					}
				}
			}
			return check;
		} catch (NotFoundException e) {
			throw new Exception(e);
		}
	}


	@Override
	public Employees findByUserId() throws Exception {
		String permissionCode = "PERMSN17";
		boolean validation = validation(permissionCode);
		if(validation) {
			return employeesDao.findByUserId(getIdAuth());
		}
		else {
			throw new Exception("Access Denied");
		}
	}


	@Override
	public List<Employees> employeesCompany() throws Exception {
		String permissionCode = "PERMSN17";
		boolean validation = validation(permissionCode);
		if(validation) {
			return employeesDao.employeesCompany(companiesCode());
		}
		else {
			throw new Exception("Access Denied");
		}
	}
	
	public String generateCode() throws Exception {
		String generatedCode = EnumCode.EMPLOYEES.getCode() + (employeesDao.countData() + 1);
		return generatedCode;
	}
}
