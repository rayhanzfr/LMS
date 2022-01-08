package com.lawencon.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.users.SaveUsersResDto;
import com.lawencon.lms.dto.users.UpdateUsersResDto;
import com.lawencon.lms.email.EmailHelper;
import com.lawencon.lms.email.PasswordSender;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.RolesService;
import com.lawencon.lms.service.UsersService;

@Service
public class UsersServiceImpl extends BaseServiceLmsImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private PasswordSender passwordSender;

	@Autowired
	private EmployeesDao employeesDao;

	@Override
	public List<Users> findAll() throws Exception {
		String permissionCode = "PERMSN37";
		boolean validation = validation(permissionCode);
		if (validation) {
			return usersDao.findAll();
		} else {
			throw new Exception("Access Denied");
		}
	}
	
	public String companiesCode()throws Exception{
		Employees employees = employeesDao.findByUserId(getIdAuth());
		String companiesCode = employees.getCompanies().getCompaniesCode();
		return companiesCode;
	}

	@Override
	public Users findById(String id) throws Exception {
		String permissionCode = "PERMSN37";
		boolean validation = validation(permissionCode);
		if (validation) {	
			return usersDao.findById(id);
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public Users findByEmail(String email) throws Exception {
		String permissionCode = "PERMSN37";
		boolean validation = validation(permissionCode);
		if (validation) {
			return usersDao.findByEmail(email);
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public SaveUsersResDto save(Users users) throws Exception {
		String permissionCode = "PERMSN38";
		boolean validation = validation(permissionCode);
		if (validation) {
			String initPassword = generateInitPassword().toString();

			SaveUsersResDto resDto = new SaveUsersResDto();
			try {
				Roles roles = rolesDao.findByCode(users.getRoles().getRolesCode());
				Users userSystem = usersDao.findByEmail("lawenconassetsmanagement@gmail.com");
				users.setCreatedBy(userSystem.getId());
				users.setRoles(roles);
				users.setUsersPassword(bCryptPasswordEncoder.encode(initPassword));
				begin();
				users = usersDao.saveOrUpdate(users);
				commit();
				resDto.setId(users.getId());
				resDto.setMessage("You was creating new User");
				
				EmailHelper emailHelper = new EmailHelper();
				emailHelper.setReceiver(users.getUsersEmail());
				emailHelper.setSubject("Password for login LMS");

				passwordSender.sendSimpleMessage(emailHelper, initPassword);
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return resDto;
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public UpdateUsersResDto update(Users users) throws Exception {
		String permissionCode = "PERMSN39";
		boolean validation = validation(permissionCode);
		if (validation) {
			UpdateUsersResDto resDto = new UpdateUsersResDto();
			try {
				Roles roles = rolesService.findByCode(users.getRoles().getRolesCode());
				users.setRoles(roles);
				Users user = findByEmail(users.getUsersEmail());
				users.setUsersPassword(bCryptPasswordEncoder.encode(users.getUsersPassword()));
				users.setCreatedAt(user.getCreatedAt());
				users.setCreatedBy(user.getCreatedBy());
				users.setUpdatedBy(getIdAuth());

				begin();
				users = usersDao.saveOrUpdate(users);
				commit();
				resDto.setVersion(users.getVersion());
				resDto.setMessage("You updating "+users.getUsersEmail());
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return resDto;
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public boolean removeById(String id) throws Exception {
		String permissionCode = "PERMSN40";
		boolean validation = validation(permissionCode);
		if (validation) {
			try {
				begin();
				boolean delete = usersDao.removeById(id);
				commit();

				return delete;
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
				throw new Exception(e);
			}
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		Users user = null;
		try {
			user = usersDao.findByEmail(arg0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getMessage());
		}
		return new User(user.getUsersEmail(), user.getUsersPassword(), new ArrayList<>());
	}

	Integer generateInitPassword() {
		int generatedPassword = (int) Math.floor(Math.random() * (99999 - 10000 + 1) + 10000);

		return generatedPassword;
	}

	public Boolean validation(String permissionsCode) throws Exception {
		try {
			Users users = usersDao.findById(getIdAuth());
			Roles roles = rolesDao.findById(users.getRoles().getId());
			Permissions permissions = permissionsDao.findByCode(permissionsCode);
			List<PermissionsRoles> listPermissionsRoles = permissionsRolesDao.findAll();
			for (int i = 0; i < listPermissionsRoles.size(); i++) {
				if (listPermissionsRoles.get(i).getPermissions().getId().equals(permissions.getId())) {
					if (listPermissionsRoles.get(i).getRoles().getId().equals(roles.getId())) {
						return true;
					}
				}
			}
			return false;
		} catch (NotFoundException e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<Users> findByCompany() throws Exception {
		String permissionCode = "PERMSN37";
		boolean validation = validation(permissionCode);
		if (validation) {
			return usersDao.findByCompany(companiesCode());
		} else {
			throw new Exception("Access Denied");
		}
	}
}
