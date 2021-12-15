package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.itemstypes.SaveItemsTypesResDto;
import com.lawencon.lms.dto.itemstypes.UpdateItemsTypesResDto;
import com.lawencon.lms.model.ItemsTypes;

public interface ItemsTypesService {
	List<ItemsTypes> findAll() throws Exception;

	ItemsTypes findById(String id) throws Exception;

	ItemsTypes findByCode(String code) throws Exception;

	SaveItemsTypesResDto save(ItemsTypes itemsTypes) throws Exception;
	
	UpdateItemsTypesResDto update(ItemsTypes itemsTypes) throws Exception;

	Boolean removeById(String id) throws Exception;
}
