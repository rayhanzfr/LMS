package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.ItemsTypesDao;
import com.lawencon.lms.model.ItemsTypes;
import com.lawencon.lms.service.ItemsTypesService;

public class ItemsTypesServiceImpl extends BaseServiceImpl implements ItemsTypesService{

	@Autowired
	private ItemsTypesDao itemsTypesDao;
	
	@Override
	public List<ItemsTypes> findAll() throws Exception {
		return itemsTypesDao.findAll();
	}

	@Override
	public ItemsTypes findById(String id) throws Exception {
		return itemsTypesDao.findById(id);
	}

	@Override
	public ItemsTypes findByCode(String code) throws Exception {
		return itemsTypesDao.findByCode(code);
	}

	@Override
	public ItemsTypes save(ItemsTypes itemsTypes) throws Exception {
		try {
			begin();
			itemsTypes = itemsTypesDao.findByCode(itemsTypes.getItemsTypesCode());
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return itemsTypes;
	}

	@Override
	public ItemsTypes update(ItemsTypes itemsTypes) throws Exception {
		try {
			ItemsTypes itemsType = itemsTypesDao.findByCode(itemsTypes.getItemsTypesCode());
			itemsTypes.setCreatedBy(itemsType.getCreatedBy());
			itemsTypes.setCreatedAt(itemsType.getCreatedAt());
			
			begin();
			itemsTypes = itemsTypesDao.findByCode(itemsTypes.getItemsTypesCode());
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return itemsTypes;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return removeById(id);
	}



}
