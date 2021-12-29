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
	
	List<Assets> findByReq(String itemsCode,String itemsTypesCode,String itemsBrandsCode, String statusesAssetsCode, String statusesInOutCode,Integer total) throws Exception;

	List<Assets> getExpiredAssets() throws Exception;

	List<Assets> getTop5AssetsDeploy() throws Exception;
	
	List<Assets> getNewAssets()throws Exception;
	
	Integer countData() throws Exception;
	
	Assets saveOrUpdate(Assets assets) throws Exception;

	Boolean removeById(String id) throws Exception;
	
}
