package com.lawencon.lms.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.ItemsTypesDao;
import com.lawencon.lms.dto.itemstypes.SaveItemsTypesResDto;
import com.lawencon.lms.dto.itemstypes.UpdateItemsTypesResDto;
import com.lawencon.lms.model.ItemsTypes;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.ItemsTypesService;
import com.lawencon.lms.service.UsersService;

@Service
public class ItemsTypesServiceImpl extends BaseServiceLmsImpl implements ItemsTypesService{

	@Autowired
	private ItemsTypesDao itemsTypesDao;
	
	@Autowired
	private UsersService usersService;
	
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
			Users users = usersService.findById(getIdAuth());
			if(users==null) {
				throw new IllegalAccessException("must login first");
			}
			else if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") && users.getIsActive() == false) {
				throw new IllegalAccessException("only superAdmin can Insert data!");
			}
			else {
				ItemsTypes itemType = new ItemsTypes();
				itemType.setCreatedBy(getIdAuth());
				itemType.setItemsTypesCode(countData());
				itemType.setItemsTypesName(itemsTypes.getItemsTypesName());
				begin();
				itemsTypes = itemsTypesDao.saveOrUpdate(itemType);
				commit();
				resDto.setId(itemsTypes.getId());
				resDto.setMessage("INSERTED");
			}

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
			itemsType.setItemsTypesName(itemsTypes.getItemsTypesName());
			itemsType.setUpdatedBy(getIdAuth());
			begin();
			itemsTypes = itemsTypesDao.saveOrUpdate(itemsType);
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

	@Override
	public String countData() throws Exception {
		int count = itemsTypesDao.count()+1;
		String result = EnumCode.ITEMSTYPE.getCode() + count;
		return result;
	}
}
