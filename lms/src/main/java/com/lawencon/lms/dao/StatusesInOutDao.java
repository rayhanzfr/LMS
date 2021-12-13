package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.StatusesInOut;


public interface StatusesInOutDao {
	List<StatusesInOut> getAll() throws Exception;
	StatusesInOut getById(String id) throws Exception;
	StatusesInOut saveOrUpdate(StatusesInOut statusesInOut) throws Exception;
	Boolean removeById(String id) throws Exception;
}
