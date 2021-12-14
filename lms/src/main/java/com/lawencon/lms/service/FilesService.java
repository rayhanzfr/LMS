package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Files;

public interface FilesService {
	List<Files> findAll() throws Exception;
	
	Files findById(String id) throws Exception;
	
	Files save(Files files) throws Exception;
	
	Files update(Files files) throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
