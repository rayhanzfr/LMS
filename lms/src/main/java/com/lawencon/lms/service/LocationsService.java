package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.locations.SaveLocationsResDto;
import com.lawencon.lms.dto.locations.UpdateLocationsResDto;
import com.lawencon.lms.model.Locations;

public interface LocationsService {
	List<Locations> findAll() throws Exception;

	List<Locations> findByCompanies() throws Exception;

	Locations findById(String id) throws Exception;

	Locations findByCode(String code) throws Exception;

	SaveLocationsResDto save(Locations locations) throws Exception;

	UpdateLocationsResDto update(Locations locations) throws Exception;
	
	Boolean removeById(String id) throws Exception;

}
