package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Files;

public interface FilesDao {
	
	Files saveOrUpdate(Files items) throws Exception;
	
	Files findById(String id) throws Exception;
	
	List<Files> findAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
