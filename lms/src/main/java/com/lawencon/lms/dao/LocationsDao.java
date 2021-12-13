package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Locations;

public interface LocationsDao {
	List<Locations> getAll() throws Exception;

	Locations getById(String id) throws Exception;

	Locations getByCode(String code) throws Exception;
	
	Locations saveOrUpdate(Locations locations) throws Exception;

	Boolean removeById(String id) throws Exception;
}
