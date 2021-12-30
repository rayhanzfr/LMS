package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Items;

public interface ItemsDao {
	
	Items saveOrUpdate(Items items) throws Exception;
	
	Items findById(String id) throws Exception;
	
	Items findByCode(String code) throws Exception;
	
	Items findByItemsBrandsAndItemsType(String itemsBrandsCode,String itemsTypesCode)throws Exception;
	
	List<Items> findAll() throws Exception;
	
	Boolean removeById(String id) throws Exception;
	
	Integer countData() throws Exception;
}
