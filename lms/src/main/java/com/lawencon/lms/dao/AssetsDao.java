package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Assets;

public interface AssetsDao {
	List<Assets> findAll() throws Exception;

	Assets findById(String id) throws Exception;

	Assets findByAssetsName(String companiesCode,String assetsName) throws Exception;
	
	List<Assets> findByStatAssetsInOut(String companiesCode,String statusesAssetsCode, String statusesInOutCode) throws Exception;
	
	List<Assets> findByItemsCode(String companiesCode,String itemsCode) throws Exception;

	List<Assets> findByBrandsCode(String companiesCode,String brandsCode) throws Exception;

	List<Assets> findByItemsTypesCode(String companiesCode,String itemsTypesCode) throws Exception;

	List<Assets> findByStatusesAssetsCode(String companiesCode,String statusesAssetsCode) throws Exception;

	List<Assets> findByStatusesInOutCode(String companiesCode,String statusesInOutCode) throws Exception;
	
	List<Assets> findByCompaniesCode(String companiesCode) throws Exception;
	
	List<Assets> findByReq(String itemsCode, String companiesCode, String statusesAssetsCode, String statusesInOutCode, Integer total) throws Exception;

	List<Assets> getExpiredAssets(String companiesCode) throws Exception;

	List<Assets> getTop5AssetsDeploy(String companiesCode) throws Exception;
	
	List<Assets> getNewAssets(String companiesCode)throws Exception;
	
	Integer countData() throws Exception;
	
	Assets saveOrUpdate(Assets assets) throws Exception;

	Boolean removeById(String id) throws Exception;
}
