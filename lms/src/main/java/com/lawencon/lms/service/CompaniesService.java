package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Companies;

public interface CompaniesService {
	List<Companies> findAll() throws Exception;

	Companies findById(String id) throws Exception;

	Companies findByCode(String code) throws Exception;
	
	Companies save(Companies companies) throws Exception;
	
	Companies update(Companies companies) throws Exception;

	Boolean removeById(String id) throws Exception;

}
