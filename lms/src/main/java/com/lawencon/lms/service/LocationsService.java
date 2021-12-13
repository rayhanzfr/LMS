package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Locations;

public interface LocationsService {

	List<Locations> getAll() throws Exception;

	Locations getById(Long id) throws Exception;

	void saveOrUpdate(Locations locations) throws Exception;

	Boolean deleteById(Long id) throws Exception;
}
