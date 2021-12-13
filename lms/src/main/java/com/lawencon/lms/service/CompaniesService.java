package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Companies;

public interface CompaniesService {

	List<Companies> getAll() throws Exception;

	Companies getById(Long id) throws Exception;

	void saveOrUpdate(Companies companies) throws Exception;

	Boolean deleteById(Long id) throws Exception;
}
