package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.ItemsTypes;

public interface ItemsTypesService {
	List<ItemsTypes> findAll() throws Exception;
	ItemsTypes findById() throws Exception;
	void saveOrUpdate(ItemsTypes itemsTypes) throws Exception;
	Boolean deleteById() throws Exception;
}
