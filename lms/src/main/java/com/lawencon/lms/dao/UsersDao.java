package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Users;

public interface UsersDao {
	List<Users> getAll() throws Exception;
	Users getById(String id) throws Exception;
	Users saveOrUpdate(Users users) throws Exception;
	Boolean removeById(String id)throws Exception;
}
