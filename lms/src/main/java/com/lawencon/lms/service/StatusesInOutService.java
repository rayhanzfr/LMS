package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.StatusesInOut;


public interface StatusesInOutService {
	List<StatusesInOut> getAll() throws Exception;
	StatusesInOut getById() throws Exception;
	void saveOrUpdate(StatusesInOut statusesInOut) throws Exception;
	Boolean deleteById() throws Exception;
}
