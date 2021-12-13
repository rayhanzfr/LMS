package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Items;

public interface ItemsDao {
	
	void saveOrUpdate(Items items) throws Exception;
	
	Items getById(String id) throws Exception;
	
	Items getByCode(String code) throws Exception;
	
	List<Items> getAll() throws Exception;
}
