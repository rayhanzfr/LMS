package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.ItemsDao;
import com.lawencon.lms.model.Files;
import com.lawencon.lms.model.Items;
import com.lawencon.lms.model.ItemsBrands;
import com.lawencon.lms.model.ItemsTypes;
import com.lawencon.lms.service.FilesService;
import com.lawencon.lms.service.ItemsBrandsService;
import com.lawencon.lms.service.ItemsService;
import com.lawencon.lms.service.ItemsTypesService;

public class ItemsServiceImpl extends BaseServiceImpl implements ItemsService {

	@Autowired
	private ItemsDao itemsDao;
	
	@Autowired
	private FilesService filesService;
	
	@Autowired
	private ItemsTypesService itemsTypesService;
		
	@Autowired
	private ItemsBrandsService itemsBrandsService;

	@Override
	public Items save(Items items) throws Exception {
		try {
			Files files = filesService.findById(items.getFiles().getId());
			ItemsTypes itemsTypes = itemsTypesService.findByCode(items.getItemsTypes().getItemsTypesCode());
			ItemsBrands itemsBrands = itemsBrandsService.findByCode(items.getItemsBrands().getItemsBrandsCode());
			items.setFiles(files);
			items.setItemsTypes(itemsTypes);
			items.setItemsBrands(itemsBrands);
			begin();
			items = itemsDao.saveOrUpdate(items);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return items;
	}

	@Override
	public Items update(Items items) throws Exception {
		try {
			Files files = filesService.findById(items.getFiles().getId());
			ItemsTypes itemsTypes = itemsTypesService.findByCode(items.getItemsTypes().getItemsTypesCode());
			ItemsBrands itemsBrands = itemsBrandsService.findByCode(items.getItemsBrands().getItemsBrandsCode());
			items.setFiles(files);
			items.setItemsTypes(itemsTypes);
			items.setItemsBrands(itemsBrands);
			Items itemsDb = findByCode(items.getItemsCode());	
			items.setCreatedAt(itemsDb.getCreatedAt());
			items.setCreatedBy(itemsDb.getCreatedBy());

			begin();
			items = itemsDao.saveOrUpdate(items);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return items;
	}

	@Override
	public Items findById(String id) throws Exception {
		return itemsDao.findById(id);
	}

	@Override
	public List<Items> findAll() throws Exception {
		return itemsDao.findAll();
	}

	@Override
	public Items findByCode(String code) throws Exception {
		return itemsDao.findByCode(code);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return itemsDao.removeById(id);
	}
}
