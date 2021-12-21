package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.CompaniesDao;
import com.lawencon.lms.dao.FilesDao;
import com.lawencon.lms.dto.companies.SaveCompaniesResDto;
import com.lawencon.lms.dto.companies.UpdateCompaniesResDto;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Files;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.CompaniesService;
import com.lawencon.lms.service.UsersService;

@Service
public class CompaniesServiceImpl extends BaseServiceLmsImpl implements CompaniesService {

	@Autowired
	private UsersService usersService;

	@Autowired
	private CompaniesDao companiesDao;

	@Autowired
	private FilesDao filesDao;

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
	public SaveCompaniesResDto save(Companies companies, MultipartFile file) throws Exception {
		SaveCompaniesResDto saveRes = new SaveCompaniesResDto();

		try {
			String img = file.getOriginalFilename();
			String ext = img.substring(img.lastIndexOf(".") + 1, img.length());
			Files filesInsert = new Files();
			filesInsert.setFile(file.getBytes());
			filesInsert.setExtensions(ext);

			Users users = usersService.findById(getIdAuth());
			if (users == null) {
				throw new IllegalAccessException("You need to login first!");
			}

			else if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") || users.getIsActive() == false) {
				saveRes.setMessage("only superAdmin can Insert data!");
				throw new IllegalAccessException("only superAdmin can Insert data!");
			}

			else {
				if(companies.getCompaniesPhone() == null) {
					throw new Exception("companiesPhone required");
				}
				
				else if(companies.getCompaniesAddress() == null) {
					throw new Exception("companiesAddress required");
				}
				
				else {
					begin();
					Files filesDb = new Files();
					filesInsert.setCreatedBy(getIdAuth());
					filesDb = filesDao.saveOrUpdate(filesInsert);
					companies.setFiles(filesDb);
					companies.setCompaniesCode(generateCode());
					companies.setCreatedBy(getIdAuth());
					companies = companiesDao.saveOrUpdate(companies);
					commit();
				}
			}

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
			Files files = filesDao.findById(companies.getFiles().getId());
			companies.setFiles(files);

			Companies companiesDb = findByCode(companies.getCompaniesCode());
			companies.setCreatedAt(companiesDb.getCreatedAt());
			companies.setCreatedBy(companiesDb.getCreatedBy());
			companies.setUpdatedBy(getIdAuth());

			Users users = usersService.findById(getIdAuth());

			if (users == null) {
				throw new IllegalAccessException("You need to login first!");
			}

			else if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") || users.getIsActive() == false) {
				updateRes.setMessage("only superAdmin can Update data!");
				throw new IllegalAccessException("only superAdmin can Update data!");
			}

			else {
				begin();
				companies = companiesDao.saveOrUpdate(companies);
				commit();

				updateRes.setVersion(companies.getVersion());
				updateRes.setMessage("Inserted");
			}

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

	public String generateCode() throws Exception {
		String generatedCode = EnumCode.COMPANIES.getCode() + (companiesDao.countData() + 1);
		return generatedCode;
	}

}
