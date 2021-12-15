package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.ItemsDao;
import com.lawencon.lms.dto.items.SaveItemsResDto;
import com.lawencon.lms.dto.items.UpdateItemsResDto;
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
	public SaveItemsResDto save(Items items,MultipartFile file) throws Exception {
		SaveItemsResDto saveItemsResDto = new SaveItemsResDto();
		try {
			String img = file.getName();
			String ext = img.substring(img.lastIndexOf(".")+1,img.length());
			Files filesInsert = new Files();
			filesInsert.setFile(file.getBytes());
			filesInsert.setExtensions(ext);
			
			begin();
			Files filesDb = new Files();
			filesDb = filesService.save(filesInsert);
			ItemsTypes itemsTypes = itemsTypesService.findByCode(items.getItemsTypes().getItemsTypesCode());
			ItemsBrands itemsBrands = itemsBrandsService.findByCode(items.getItemsBrands().getItemsBrandsCode());
			items.setItemsCode(generateCode());
			items.setFiles(filesDb);
			items.setItemsTypes(itemsTypes);
			items.setItemsBrands(itemsBrands);
			items = itemsDao.saveOrUpdate(items);
			commit();
			saveItemsResDto.setId(itemsBrands.getId());
			saveItemsResDto.setMsg("OK");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return saveItemsResDto;
	}

	@Override
	public UpdateItemsResDto update(Items items) throws Exception {
		UpdateItemsResDto updateItemsResDto = new UpdateItemsResDto();
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
			updateItemsResDto.setVersion(itemsBrands.getVersion());
			updateItemsResDto.setMsg("OK");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateItemsResDto;
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
		try {
			begin();
			boolean isDeleted = itemsDao.removeById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public String generateCode()throws Exception{
		Integer increment = itemsDao.countData();
		String code= EnumCode.ITEMS.getCode()+increment;
		return code;
	}
}
