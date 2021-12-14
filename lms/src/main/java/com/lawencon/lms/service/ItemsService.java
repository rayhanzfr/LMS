package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Items;

public interface ItemsService {
	List<Items> findAll() throws Exception;
	
	Items findById(String id) throws Exception;
	
	Items save(Items itemsBrands) throws Exception;
	
	Items update(Items itemsBrands) throws Exception;
	
	Items findByCode(String code) throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
