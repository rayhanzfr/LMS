package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.ItemsTypes;

public interface ItemsTypesDao {
	List<ItemsTypes> findAll() throws Exception;

	ItemsTypes findById(String id) throws Exception;

	ItemsTypes findByCode(String code) throws Exception;

	ItemsTypes saveOrUpdate(ItemsTypes itemsTypes) throws Exception;

	Boolean removeById(String id) throws Exception;
}
