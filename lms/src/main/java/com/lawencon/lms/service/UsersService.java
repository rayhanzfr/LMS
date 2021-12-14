package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Users;

public interface UsersService {
	List<Users> findAll() throws Exception;
	Users findById() throws Exception;
	void saveOrUpdate(Users users) throws Exception;
	Boolean deleteById()throws Exception;
}
