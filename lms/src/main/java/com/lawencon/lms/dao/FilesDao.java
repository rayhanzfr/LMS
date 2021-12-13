package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Files;

public interface FilesDao {
	
	void saveOrUpdate(Files items) throws Exception;
	
	Files getById(String id) throws Exception;
	
	List<Files> getAll() throws Exception;
}
