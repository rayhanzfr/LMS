package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.users.SaveUsersResDto;
import com.lawencon.lms.dto.users.UpdateUsersResDto;
import com.lawencon.lms.model.Users;

public interface UsersService {
	List<Users> findAll() throws Exception;

	Users findById(String id) throws Exception;

	Users findByEmail(String email) throws Exception;

	SaveUsersResDto save(Users users) throws Exception;

	UpdateUsersResDto update(Users users) throws Exception;

	boolean removeById(String id) throws Exception;
}
