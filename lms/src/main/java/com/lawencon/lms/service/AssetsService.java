package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Assets;

public interface AssetsService {
	List<Assets> findAll() throws Exception;

	Assets findById(String id) throws Exception;

	Assets findByAssetsName(String assetsName) throws Exception;

	List<Assets> findByItemsCode(String itemsCode) throws Exception;

	List<Assets> findByBrandsCode(String brandsCode) throws Exception;

	List<Assets> findByItemsTypesCode(String itemsTypesCode) throws Exception;

	List<Assets> findByStatusesAssetsCode(String statusesAssetsCode) throws Exception;

	List<Assets> findByStatusesInOutCode(String statusesInOutCode) throws Exception;

	Assets save(Assets assets) throws Exception;
	
	Assets update(Assets assets) throws Exception;
	
	Assets updateStatusAssets(Assets assets) throws Exception;
	
	Assets updateStatusInOut(Assets assets) throws Exception;

	Boolean removeById(String id) throws Exception;
}
