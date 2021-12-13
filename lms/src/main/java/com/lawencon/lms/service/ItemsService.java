package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Items;

public interface ItemsService {
Items saveOrUpdate(Items items) throws Exception;
	
	Items getById(String id) throws Exception;
	
	Items getByCode(String code) throws Exception;
	
	List<Items> getAll() throws Exception;
	
	void removeById(String id) throws Exception;
}
