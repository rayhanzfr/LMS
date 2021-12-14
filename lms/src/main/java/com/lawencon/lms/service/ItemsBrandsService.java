package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.ItemsBrands;

public interface ItemsBrandsService {
	
	List<ItemsBrands> findAll() throws Exception;
	
	ItemsBrands findById(String id) throws Exception;
	
	ItemsBrands save(ItemsBrands itemsBrands) throws Exception;
	
	ItemsBrands update(ItemsBrands itemsBrands) throws Exception;
	
	ItemsBrands findByCode(String code) throws Exception;
	
	Boolean removeById(String id) throws Exception;
}
