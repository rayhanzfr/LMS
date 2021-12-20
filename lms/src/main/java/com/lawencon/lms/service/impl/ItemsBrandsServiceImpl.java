package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.ItemsBrandsDao;
import com.lawencon.lms.dto.itemsbrands.SaveItemsBrandsResDto;
import com.lawencon.lms.dto.itemsbrands.UpdateItemsBrandsResDto;
import com.lawencon.lms.model.ItemsBrands;
import com.lawencon.lms.service.ItemsBrandsService;

@Service
public class ItemsBrandsServiceImpl extends BaseServiceLmsImpl implements ItemsBrandsService {

	@Autowired
	private ItemsBrandsDao itemsBrandsDao;

	@Override
	public SaveItemsBrandsResDto save(ItemsBrands itemsBrands) throws Exception {
		SaveItemsBrandsResDto saveItemsBrandsResDto = new SaveItemsBrandsResDto();
		try {
			begin();
			itemsBrands.setItemsBrandsCode(generateCode());
			itemsBrands.setCreatedBy(getIdAuth());
			itemsBrands = itemsBrandsDao.saveOrUpdate(itemsBrands);
			commit();
			saveItemsBrandsResDto.setId(itemsBrands.getId());
			saveItemsBrandsResDto.setMsg("OK");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return saveItemsBrandsResDto;
	}

	@Override
	public UpdateItemsBrandsResDto update(ItemsBrands itemsBrands) throws Exception {
		UpdateItemsBrandsResDto updateItemsBrandsResDto = new UpdateItemsBrandsResDto();
		try {
			ItemsBrands itemsBrandsDb = findByCode(itemsBrands.getItemsBrandsCode());
			itemsBrands.setCreatedAt(itemsBrandsDb.getCreatedAt());
			itemsBrands.setCreatedBy(itemsBrandsDb.getCreatedBy());
			itemsBrands.setUpdatedBy(getIdAuth());
			begin();
			itemsBrands = itemsBrandsDao.saveOrUpdate(itemsBrands);
			commit();
			updateItemsBrandsResDto.setVersion(itemsBrands.getVersion());
			updateItemsBrandsResDto.setMsg("OK");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateItemsBrandsResDto;
	}

	@Override
	public ItemsBrands findById(String id) throws Exception {
		return itemsBrandsDao.findById(id);
	}

	@Override
	public List<ItemsBrands> findAll() throws Exception {
		return itemsBrandsDao.findAll();
	}

	@Override
	public ItemsBrands findByCode(String code) throws Exception {
		return itemsBrandsDao.findByCode(code);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = itemsBrandsDao.removeById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public String generateCode() throws Exception {
		Integer increment = itemsBrandsDao.countData() + 1;
		String code = EnumCode.ITEMBRANDS.getCode() + increment;
		return code;
	}
}
