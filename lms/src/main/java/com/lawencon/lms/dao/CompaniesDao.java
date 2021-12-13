package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Companies;

public interface CompaniesDao {
	List<Companies> getAll() throws Exception;

	Companies getById(String id) throws Exception;

	Companies getByCode(String code) throws Exception;
	
	Companies saveOrUpdate(Companies companies) throws Exception;

	Boolean removeById(String id) throws Exception;
}
