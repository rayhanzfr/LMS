package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.ItemsTypesDao;
import com.lawencon.lms.dto.itemstypes.SaveItemsTypesResDto;
import com.lawencon.lms.dto.itemstypes.UpdateItemsTypesResDto;
import com.lawencon.lms.model.ItemsTypes;
import com.lawencon.lms.service.ItemsTypesService;

@Service
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
	public SaveItemsTypesResDto save(ItemsTypes itemsTypes) throws Exception {
		SaveItemsTypesResDto resDto = new SaveItemsTypesResDto();
		try {
			begin();
			itemsTypes = itemsTypesDao.findByCode(itemsTypes.getItemsTypesCode());
			commit();
			resDto.setId(itemsTypes.getId());
			resDto.setMessage("INSERTED");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return resDto;
	}

	@Override
	public UpdateItemsTypesResDto update(ItemsTypes itemsTypes) throws Exception {
		UpdateItemsTypesResDto resDto = new UpdateItemsTypesResDto();
		try {
			ItemsTypes itemsType = itemsTypesDao.findByCode(itemsTypes.getItemsTypesCode());
			itemsTypes.setCreatedBy(itemsType.getCreatedBy());
			itemsTypes.setCreatedAt(itemsType.getCreatedAt());
			
			begin();
			itemsTypes = itemsTypesDao.findByCode(itemsTypes.getItemsTypesCode());
			commit();
			resDto.setVersion(itemsTypes.getVersion());
			resDto.setMessage("UPDATED");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return resDto;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		try {
			begin();
			boolean delete = itemsTypesDao.removeById(id);
			commit();
			
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
