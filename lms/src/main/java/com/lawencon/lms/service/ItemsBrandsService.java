package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.ItemsBrands;

public interface ItemsBrandsService {
	List<ItemsBrands> findAll() throws Exception;
	ItemsBrands findById() throws Exception;
	void saveOrUpdate(ItemsBrands itemsBrands) throws Exception;
	Boolean deleteById() throws Exception;
}
