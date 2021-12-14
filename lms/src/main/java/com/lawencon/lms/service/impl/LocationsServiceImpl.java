package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.LocationsDao;
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
	public Locations save(Locations locations) throws Exception {
		try {
			Companies companies = companiesService.findByCode(locations.getCompanies().getCompaniesCode());
			locations.setCompanies(companies);
			
			begin();
			locations = locationsDao.saveOrUpdate(locations);
			commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return locations;
	}

	@Override
	public Locations update(Locations locations) throws Exception {
		try {
			Companies companies = companiesService.findByCode(locations.getCompanies().getCompaniesCode());
			locations.setCompanies(companies);
			
			Locations locationsDb = findByCode(locations.getLocationsCode());
			locations.setCreatedAt(locationsDb.getCreatedAt());
			locations.setCreatedBy(locationsDb.getCreatedBy());
			
			begin();
			locations = locationsDao.saveOrUpdate(locations);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return locations;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return locationsDao.removeById(id);
	}

}
