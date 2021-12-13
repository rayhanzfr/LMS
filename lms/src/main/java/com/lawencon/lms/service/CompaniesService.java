package com.lawencon.lms.service;

import com.lawencon.lms.model.Companies;

public interface CompaniesService {

	void getAll() throws Exception;

	void getById(Long id) throws Exception;

	Companies insert(Companies companies) throws Exception;

	Companies update(Companies companies) throws Exception;

	void deleteById(Long id) throws Exception;
}
