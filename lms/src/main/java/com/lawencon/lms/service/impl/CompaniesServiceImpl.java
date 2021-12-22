package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.CompaniesDao;
import com.lawencon.lms.dao.FilesDao;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.companies.SaveCompaniesResDto;
import com.lawencon.lms.dto.companies.UpdateCompaniesResDto;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Files;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.CompaniesService;

@Service
public class CompaniesServiceImpl extends BaseServiceLmsImpl implements CompaniesService {

	@Autowired
	private CompaniesDao companiesDao;

	@Autowired
	private FilesDao filesDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Override
	public List<Companies> findAll() throws Exception {
		String permissionsCode = "PERMSN13";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return companiesDao.findAll();
		throw new Exception("Access Denied");
	}

	@Override
	public Companies findById(String id) throws Exception {
		String permissionsCode = "PERMSN13";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return companiesDao.findById(id);
		throw new Exception("Access Denied");
	}

	@Override
	public Companies findByCode(String code) throws Exception {

		String permissionsCode = "PERMSN13";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return companiesDao.findByCode(code);
		throw new Exception("Access Denied");
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

			String permissionsCode = "PERMSN14";
			Boolean validation = validationUsers(permissionsCode);
			if (!validation) {
				throw new Exception("Access Denied");
			}

			else {
				if (companies.getCompaniesPhone() == null) {
					throw new Exception("companiesPhone required");
				}

				else if (companies.getCompaniesAddress() == null) {
					throw new Exception("companiesAddress required");
				}
				
				else if(companies.getCompaniesName() == null) {
					throw new Exception("companiesName required");
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
			String img = file.getOriginalFilename();
			String ext = img.substring(img.lastIndexOf(".") + 1, img.length());
			Files filesUpdate = new Files();
			filesUpdate.setFile(file.getBytes());
			filesUpdate.setExtensions(ext);

			String permissionsCode = "PERMSN15";
			Boolean validation = validationUsers(permissionsCode);
			if (!validation) {
				throw new Exception("Access Denied");
			}

			else {
				Companies companiesDb = findByCode(companies.getCompaniesCode());
				if (companiesDb == null) {
					throw new Exception("Companies code not found");
				}

				else {
					companiesDb.setUpdatedBy(getIdAuth());
					companiesDb.setCompaniesName(companies.getCompaniesName());
					companiesDb.setCompaniesPhone(companies.getCompaniesPhone());
					companiesDb.setCompaniesAddress(companies.getCompaniesAddress());

					if (companiesDb.getCompaniesName() == null) {
						throw new Exception("companiesName required");
					}
					
					else if (companiesDb.getCompaniesPhone() == null) {
						throw new Exception("companiesPhone required");
					}
					
					else if(companiesDb.getCompaniesAddress() == null) {
						throw new Exception("companiesAddress required");
					}

					else {
						begin();
						Files filesDb = new Files();
						filesUpdate.setCreatedBy(getIdAuth());
						filesDb = filesDao.saveOrUpdate(filesUpdate);
						companiesDb.setFiles(filesDb);

						companies = companiesDao.saveOrUpdate(companiesDb);
						commit();

						updateRes.setVersion(companies.getVersion());
						updateRes.setMessage("Inserted");
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateRes;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		String permissionsCode = "PERMSN24";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			try {
				begin();
				boolean isDeleted = companiesDao.removeById(id);
				commit();
				return isDeleted;
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
				throw new Exception(e);
			}
		}

		throw new Exception("Access Denied");
	}

	public String generateCode() throws Exception {
		String generatedCode = EnumCode.COMPANIES.getCode() + (companiesDao.countData() + 1);
		return generatedCode;
	}

	public Boolean validationUsers(String permissionsCode) throws Exception {
		try {
			Users users = usersDao.findById(getIdAuth());
			Roles roles = rolesDao.findById(users.getRoles().getId());
			Permissions permissions = permissionsDao.findByCode(permissionsCode);
			List<PermissionsRoles> listPermissionsRoles = permissionsRolesDao.findAll();
			for (int i = 0; i < listPermissionsRoles.size(); i++) {
				if (listPermissionsRoles.get(i).getPermissions().getId().equals(permissions.getId())) {
					if (listPermissionsRoles.get(i).getRoles().getId().equals(roles.getId())) {
						return true;
					}
				}
			}
			return false;
		} catch (NotFoundException e) {
			throw new Exception(e);
		}
	}

}
