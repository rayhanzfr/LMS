package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Users;

public interface UsersDao {
	List<Users> findAll() throws Exception;

	Users findById(String id) throws Exception;

	Users findByEmail(String email) throws Exception;
	
	Users saveOrUpdate(Users users) throws Exception;

	Boolean removeById(String id) throws Exception;
}
