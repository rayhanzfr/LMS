package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.users.SaveUsersResDto;
import com.lawencon.lms.dto.users.UpdateUsersResDto;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.RolesService;
import com.lawencon.lms.service.UsersService;

public class UsersServiceImpl extends BaseServiceImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private RolesService rolesService;
	
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
		SaveUsersResDto resDto = new SaveUsersResDto();
		try {
			Roles roles = rolesService.findByCode(users.getRoles().getRolesCode());
			users.setRoles(roles);
			begin();
			users = usersDao.saveOrUpdate(users);
			commit();
			resDto.setId(users.getId());
			resDto.setMessage("INSERTED");
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
			users=usersDao.saveOrUpdate(users);
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

}
