package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.ItemsBrands;

public interface ItemsBrandsDao {
	List<ItemsBrands> findAll() throws Exception;
	ItemsBrands findById(String id) throws Exception;
	ItemsBrands findByCode(String code) throws Exception;
	ItemsBrands saveOrUpdate(ItemsBrands itemsBrands) throws Exception;
	Boolean removeById(String id) throws Exception;
}
