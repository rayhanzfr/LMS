package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Files;

public interface FilesDao {
	
	Files saveOrUpdate(Files items) throws Exception;
	
	Files getById(String id) throws Exception;
	
	List<Files> getAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
