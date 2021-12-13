package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Companies;

public interface CompaniesService {

	List<Companies> getAll() throws Exception;

	Companies getById(Long id) throws Exception;

	Companies insert(Companies companies) throws Exception;

	Companies update(Companies companies) throws Exception;

	Boolean deleteById(Long id) throws Exception;
}
