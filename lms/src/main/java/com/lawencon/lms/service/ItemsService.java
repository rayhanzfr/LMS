package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Items;

public interface ItemsService {
	Items saveOrUpdate(Items items) throws Exception;
	
	Items findById(String id) throws Exception;
	
	Items findByCode(String code) throws Exception;
	
	List<Items> findAll() throws Exception;
	
	void removeById(String id) throws Exception;
}
