package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.dao.LocationsDao;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.locations.SaveLocationsResDto;
import com.lawencon.lms.dto.locations.UpdateLocationsResDto;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.CompaniesService;
import com.lawencon.lms.service.LocationsService;

@Service
public class LocationsServiceImpl extends BaseServiceLmsImpl implements LocationsService {

	@Autowired
	private LocationsDao locationsDao;

	@Autowired
	private CompaniesService companiesService;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;
	
	@Autowired
	private EmployeesDao employeeDao;
	
	private String comapniesCode() throws Exception{
		Employees employee = employeeDao.findByUserId(getIdAuth());
		String companiesCode = employee.getCompanies().getCompaniesCode();
		return companiesCode;
	}

	@Override
	public List<Locations> findAll() throws Exception {
		String permissionsCode = "PERMSN53";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return locationsDao.findAll();
		throw new Exception("Access Denied");
	}

	@Override
	public Locations findById(String id) throws Exception {

		String permissionsCode = "PERMSN53";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return locationsDao.findById(id);
		throw new Exception("Access Denied");
	}

	@Override
	public Locations findByCode(String code) throws Exception {

		String permissionsCode = "PERMSN53";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return locationsDao.findByCode(code);
		throw new Exception("Access Denied");
	}

	@Override
	public SaveLocationsResDto save(Locations locations) throws Exception {
		SaveLocationsResDto saveRes = new SaveLocationsResDto();

		try {
			String permissionsCode = "PERMSN54";
			Boolean validation = validationUsers(permissionsCode);
			if (!validation) {
				throw new Exception("Access Denied");
			}

			else {
				Companies companies = companiesService.findByCode(locations.getCompanies().getCompaniesCode());

				if (companies == null) {
					throw new Exception("Companies code not found");
				}

				else if (locations.getLocationsDeploy() == null) {
					throw new Exception("locationsDeploy required");
				}

				else {
					begin();
					locations.setCompanies(companies);
					locations.setCreatedBy(getIdAuth());
					locations.setLocationsCode(generateCode());
					locations = locationsDao.saveOrUpdate(locations);
					commit();
				}

			}

			saveRes.setId(locations.getId());
			saveRes.setMessage("Inserted");

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return saveRes;
	}

	@Override
	public UpdateLocationsResDto update(Locations locations) throws Exception {
		UpdateLocationsResDto updateRes = new UpdateLocationsResDto();

		try {
			Locations locationsDb = findByCode(locations.getLocationsCode());
			Companies companies = companiesService.findByCode(locations.getCompanies().getCompaniesCode());
			locationsDb.setCompanies(companies);
			locationsDb.setUpdatedBy(getIdAuth());
			locationsDb.setLocationsDeploy(locations.getLocationsDeploy());

			String permissionsCode = "PERMSN55";
			Boolean validation = validationUsers(permissionsCode);
			if (!validation) {
				throw new Exception("Access Denied");
			}

			else {
				if (locationsDb.getLocationsDeploy() == null) {
					throw new Exception("locationsDeploy required");
				} else {
					begin();
					locations = locationsDao.saveOrUpdate(locationsDb);
					commit();
					updateRes.setVersion(locations.getVersion());
					updateRes.setMessage("Inserted");
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

		String permissionsCode = "PERMSN56";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			try {
				begin();
				boolean isDeleted = locationsDao.removeById(id);
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
		String generatedCode = EnumCode.LOCATIONS.getCode() + (locationsDao.countData() + 1);
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

	@Override
	public List<Locations> findByCompanies() throws Exception {
		String permissionsCode = "PERMSN53";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return locationsDao.findByCompany(comapniesCode());
		throw new Exception("Access Denied");
	}
}
