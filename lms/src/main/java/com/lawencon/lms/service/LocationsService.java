package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Locations;

public interface LocationsService {
	List<Locations> findAll() throws Exception;

	Locations findById(String id) throws Exception;

	Locations findByCode(String code) throws Exception;

	Locations save(Locations locations) throws Exception;

	Locations update(Locations locations) throws Exception;
	
	Boolean removeById(String id) throws Exception;

}
