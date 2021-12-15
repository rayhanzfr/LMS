package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.LocationsDao;
import com.lawencon.lms.dto.locations.SaveLocationsResDto;
import com.lawencon.lms.dto.locations.UpdateLocationsResDto;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.service.CompaniesService;
import com.lawencon.lms.service.LocationsService;

public class LocationsServiceImpl extends BaseServiceImpl implements LocationsService {

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

			begin();
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

}
