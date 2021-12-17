package com.lawencon.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.users.SaveUsersResDto;
import com.lawencon.lms.dto.users.UpdateUsersResDto;
import com.lawencon.lms.email.PasswordSender;
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
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private PasswordSender passwordSender;

	@Override
	public List<Users> findAll() throws Exception {
		return usersDao.findAll();
	}

	@Override
	public Users findById(String id) throws Exception {
		return usersDao.findById(id);
	}

	@Override
	public Users findByEmail(String email) throws Exception {
		return usersDao.findByEmail(email);
	}

	@Override
	public SaveUsersResDto save(Users users) throws Exception {
		String initPassword = generateInitPassword().toString();

		SaveUsersResDto resDto = new SaveUsersResDto();
		try {
			Roles roles = rolesService.findByCode(users.getRoles().getRolesCode());
			users.setCreatedBy(roles.getId());
			users.setRoles(roles);
//			users.setUsersPassword(bCryptPasswordEncoder.encode(users.getUsersPassword()));
			users.setUsersPassword(bCryptPasswordEncoder.encode(initPassword));
			begin();
			users = usersDao.saveOrUpdate(users);
			commit();
			resDto.setId(users.getId());
			resDto.setMessage("INSERTED");

			passwordSender.sendSimpleMessage(users.getUsersEmail(), "Password untuk login LMS", initPassword);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return resDto;
	}

	@Override
	public UpdateUsersResDto update(Users users) throws Exception {
		UpdateUsersResDto resDto = new UpdateUsersResDto();
		try {
			Roles roles = rolesService.findByCode(users.getRoles().getRolesCode());
			users.setRoles(roles);
			Users user = findByEmail(users.getUsersEmail());
			users.setCreatedAt(user.getCreatedAt());
			users.setCreatedBy(user.getCreatedBy());

			begin();
			users = usersDao.saveOrUpdate(users);
			commit();
			resDto.setVersion(users.getVersion());
			resDto.setMessage("UPDATED");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return resDto;
	}

	@Override
	public boolean removeById(String id) throws Exception {
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
}
