package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.ItemsTypes;

public interface ItemsTypesService {
	List<ItemsTypes> getAll() throws Exception;
	ItemsTypes getById() throws Exception;
	void saveOrUpdate(ItemsTypes itemsTypes) throws Exception;
	Boolean deleteById() throws Exception;
}
