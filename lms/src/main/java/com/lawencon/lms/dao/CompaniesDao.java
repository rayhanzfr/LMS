package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Companies;

public interface CompaniesDao {
	List<Companies> findAll() throws Exception;

	Companies findById(String id) throws Exception;

	Companies findByCode(String code) throws Exception;
	
	Companies saveOrUpdate(Companies companies) throws Exception;
	
	Integer countData() throws Exception;

	Boolean removeById(String id) throws Exception;
}
