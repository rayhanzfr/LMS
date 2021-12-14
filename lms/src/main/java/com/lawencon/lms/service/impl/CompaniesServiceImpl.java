package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.CompaniesDao;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Files;
import com.lawencon.lms.service.CompaniesService;
import com.lawencon.lms.service.FilesService;

public class CompaniesServiceImpl extends BaseServiceImpl implements CompaniesService {

	@Autowired
	private CompaniesDao companiesDao;

	@Autowired
	private FilesService filesService;

	@Override
	public List<Companies> findAll() throws Exception {
		return companiesDao.findAll();
	}

	@Override
	public Companies findById(String id) throws Exception {
		return companiesDao.findById(id);
	}

	@Override
	public Companies findByCode(String code) throws Exception {
		return companiesDao.findByCode(code);
	}

	@Override
	public Companies save(Companies companies) throws Exception {
		try {
			Files files = filesService.findById(companies.getFiles().getId());
			companies.setFiles(files);

			begin();
			companies = companiesDao.saveOrUpdate(companies);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return companies;
	}

	@Override
	public Companies update(Companies companies) throws Exception {
		try {
			Files files = filesService.findById(companies.getFiles().getId());
			companies.setFiles(files);
			
			Companies companiesDb = findByCode(companies.getCompaniesCode());
			companies.setCreatedAt(companiesDb.getCreatedAt());
			companies.setCreatedBy(companiesDb.getCreatedBy());

			begin();
			companies = companiesDao.saveOrUpdate(companies);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return companies;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return companiesDao.removeById(id);
	}

}
