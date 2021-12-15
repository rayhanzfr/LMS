package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.StatusesInOut;

public interface StatusesInOutService {
	List<StatusesInOut> findAll() throws Exception;

	StatusesInOut findById(String id) throws Exception;

	StatusesInOut findByCode(String code) throws Exception;

	StatusesInOut save(StatusesInOut statusesInOut) throws Exception;

	StatusesInOut update(StatusesInOut statusesInOut) throws Exception;

	Boolean removeById(String id) throws Exception;
}
