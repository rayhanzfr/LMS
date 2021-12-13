package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Users;

public interface UsersService {
	List<Users> getAll() throws Exception;
	Users getById() throws Exception;
	void saveOrUpdate(Users users) throws Exception;
	Boolean deleteById()throws Exception;
}
