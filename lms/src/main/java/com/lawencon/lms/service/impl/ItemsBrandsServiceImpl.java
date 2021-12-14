package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.ItemsBrandsDao;
import com.lawencon.lms.model.ItemsBrands;
import com.lawencon.lms.service.ItemsBrandsService;

public class ItemsBrandsServiceImpl extends BaseServiceImpl implements ItemsBrandsService {

	@Autowired
	private ItemsBrandsDao itemsBrandsDao;

	@Override
	public ItemsBrands save(ItemsBrands itemsBrands) throws Exception {
		try {
			
			begin();
			itemsBrands=itemsBrandsDao.saveOrUpdate(itemsBrands);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return itemsBrands;
	}

	@Override
	public ItemsBrands update(ItemsBrands itemsBrands) throws Exception {
		try {
			ItemsBrands itemsBrandsDb = findById(itemsBrands.getId());	
			itemsBrands.setCreatedAt(itemsBrandsDb.getCreatedAt());
			itemsBrands.setCreatedBy(itemsBrandsDb.getCreatedBy());

			begin();
			itemsBrands=itemsBrandsDao.saveOrUpdate(itemsBrands);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return itemsBrands;
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
		return itemsBrandsDao.removeById(id);
	}
}
