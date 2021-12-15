package com.lawencon.lms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.dto.companies.SaveCompaniesResDto;
import com.lawencon.lms.dto.companies.UpdateCompaniesResDto;
import com.lawencon.lms.model.Companies;

public interface CompaniesService {
	List<Companies> findAll() throws Exception;

	Companies findById(String id) throws Exception;

	Companies findByCode(String code) throws Exception;
	
	String generateCode() throws Exception;
	
	SaveCompaniesResDto save(Companies companies, MultipartFile file) throws Exception;
	
	UpdateCompaniesResDto update(Companies companies) throws Exception;

	Boolean removeById(String id) throws Exception;

}
