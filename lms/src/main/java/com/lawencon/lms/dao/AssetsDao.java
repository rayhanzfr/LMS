package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Assets;

public interface AssetsDao {
	List<Assets> findAll() throws Exception;

	Assets findById(String id) throws Exception;

	Assets findByAssetsName(String assetsName) throws Exception;

	List<Assets> findByItemsCode(String itemsCode) throws Exception;

	List<Assets> findByBrandsCode(String brandsCode) throws Exception;

	List<Assets> findByItemsTypesCode(String itemsTypesCode) throws Exception;

	List<Assets> findByStatusesAssetsCode(String statusesAssetsCode) throws Exception;

	List<Assets> findByStatusesInOutCode(String statusesInOutCode) throws Exception;

	Assets saveOrUpdate(Assets assets) throws Exception;

	Boolean removeById(String id) throws Exception;
}
