package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.CompaniesDao;
import com.lawencon.lms.dto.companies.SaveCompaniesResDto;
import com.lawencon.lms.dto.companies.UpdateCompaniesResDto;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Files;
import com.lawencon.lms.service.CompaniesService;
import com.lawencon.lms.service.FilesService;

@Service
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
	public String generateCode() throws Exception {
		String generatedCode = companiesDao.countData() + EnumCode.COMPANIES.getCode();

		return generatedCode;
	}

	@Override
	public SaveCompaniesResDto save(Companies companies, MultipartFile file) throws Exception {
		SaveCompaniesResDto saveRes = new SaveCompaniesResDto();

		try {
			begin();
			Files files = new Files();
            files.setFile(file.getBytes());
            String ext = file.getOriginalFilename();
            ext = ext.substring(ext.lastIndexOf(".") + 1, ext.length());
            files.setExtensions(ext);
            files = filesService.save(files);
			companies.setFiles(files);
			companies = companiesDao.saveOrUpdate(companies);
			commit();

			saveRes.setId(companies.getId());
			saveRes.setMessage("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return saveRes;
	}

	@Override
	public UpdateCompaniesResDto update(Companies companies, MultipartFile file) throws Exception {
		UpdateCompaniesResDto updateRes = new UpdateCompaniesResDto();

		try {
			Files files = filesService.findById(companies.getFiles().getId());
			companies.setFiles(files);

			Companies companiesDb = findByCode(companies.getCompaniesCode());
			companies.setCreatedAt(companiesDb.getCreatedAt());
			companies.setCreatedBy(companiesDb.getCreatedBy());

			begin();
			companies = companiesDao.saveOrUpdate(companies);
			commit();

			updateRes.setVersion(companies.getVersion());
			updateRes.setMessage("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateRes;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return companiesDao.removeById(id);
	}

}
