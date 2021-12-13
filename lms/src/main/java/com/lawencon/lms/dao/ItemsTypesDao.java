package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.ItemsTypes;

public interface ItemsTypesDao {
	List<ItemsTypes> getAll() throws Exception;
	ItemsTypes getById(String id) throws Exception;
	ItemsTypes getByCode(String code) throws Exception;
	ItemsTypes saveOrUpdate(ItemsTypes itemsTypes) throws Exception;
	Boolean removeById(String id) throws Exception;
}
