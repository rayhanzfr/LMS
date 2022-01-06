package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Locations;

public interface LocationsDao {
	List<Locations> findAll() throws Exception;

	Locations findById(String id) throws Exception;

	Locations findByCode(String code) throws Exception;
	
	List<Locations>findByCompany(String companiesCode) throws Exception;
	
	Locations saveOrUpdate(Locations locations) throws Exception;

	Integer countData() throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
