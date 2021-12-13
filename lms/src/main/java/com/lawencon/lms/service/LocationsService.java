package com.lawencon.lms.service;

import com.lawencon.lms.model.Locations;

public interface LocationsService {

	void getAll() throws Exception;

	void getById(Long id) throws Exception;

	Locations insert(Locations locations) throws Exception;

	Locations update(Locations locations) throws Exception;

	void deleteById(Long id) throws Exception;
}
