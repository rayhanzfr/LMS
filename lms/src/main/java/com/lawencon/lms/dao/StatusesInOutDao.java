package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.StatusesInOut;


public interface StatusesInOutDao {
	List<StatusesInOut> findAll() throws Exception;
	StatusesInOut findById(String id) throws Exception;
	StatusesInOut findByCode(String code) throws Exception;
	StatusesInOut saveOrUpdate(StatusesInOut statusesInOut) throws Exception;
	Boolean removeById(String id) throws Exception;
}
