package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.LocationsDao;
import com.lawencon.lms.dto.locations.SaveLocationsResDto;
import com.lawencon.lms.dto.locations.UpdateLocationsResDto;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.CompaniesService;
import com.lawencon.lms.service.LocationsService;
import com.lawencon.lms.service.UsersService;

@Service
public class LocationsServiceImpl extends BaseServiceLmsImpl implements LocationsService {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private LocationsDao locationsDao;

	@Autowired
	private CompaniesService companiesService;

	@Override
	public List<Locations> findAll() throws Exception {
		return locationsDao.findAll();
	}

	@Override
	public Locations findById(String id) throws Exception {
		return locationsDao.findById(id);
	}

	@Override
	public Locations findByCode(String code) throws Exception {
		return locationsDao.findByCode(code);
	}

	@Override
	public SaveLocationsResDto save(Locations locations) throws Exception {
		SaveLocationsResDto saveRes = new SaveLocationsResDto();

		try {
			Companies companies = companiesService.findByCode(locations.getCompanies().getCompaniesCode());
			locations.setCompanies(companies);

			Users users = usersService.findById(locations.getCreatedBy());
			if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") && users.getIsActive() == false) {
				throw new IllegalAccessException("only superAdmin can Insert data!");
			}
			
			begin();
			locations.setLocationsCode(generateCode());
			locations = locationsDao.saveOrUpdate(locations);
			commit();

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
			Companies companies = companiesService.findByCode(locations.getCompanies().getCompaniesCode());
			locations.setCompanies(companies);

			Locations locationsDb = findByCode(locations.getLocationsCode());
			locations.setCreatedAt(locationsDb.getCreatedAt());
			locations.setCreatedBy(locationsDb.getCreatedBy());

			Users users = usersService.findById(locations.getUpdatedBy());
			if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") && users.getIsActive() == false) {
				throw new IllegalAccessException("only superAdmin can Update data!");
			}
			
			begin();
			locations = locationsDao.saveOrUpdate(locations);
			commit();

			updateRes.setVersion(locations.getVersion());
			updateRes.setMessage("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateRes;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return locationsDao.removeById(id);
	}

	public String generateCode() throws Exception {
		String generatedCode = locationsDao.countData() + EnumCode.LOCATIONS.getCode();
		return generatedCode;
	}
}
