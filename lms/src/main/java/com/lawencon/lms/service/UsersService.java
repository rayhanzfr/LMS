package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Users;

public interface UsersService {
	List<Users> findAll() throws Exception;

	Users findById(String id) throws Exception;

	Users findByEmail(String email) throws Exception;

	Users save(Users users) throws Exception;

	Users update(Users users) throws Exception;

	Boolean removeById(String id) throws Exception;
}
