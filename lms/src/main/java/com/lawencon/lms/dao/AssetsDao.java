package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Assets;

public interface AssetsDao {
	List<Assets> findAll() throws Exception;

	Assets findById(String id) throws Exception;
	
//	dao for non-admin

	Assets findByAssetsNameCompany(String companiesCode,String assetsName) throws Exception;
	
	List<Assets> findByStatAssetsInOutCompany(String companiesCode,String statusesAssetsCode, String statusesInOutCode) throws Exception;
	
	List<Assets> findByItemsCodeCompany(String companiesCode,String itemsCode) throws Exception;

	List<Assets> findByBrandsCodeCompany(String companiesCode,String brandsCode) throws Exception;

	List<Assets> findByItemsTypesCodeCompany(String companiesCode,String itemsTypesCode) throws Exception;

	List<Assets> findByStatusesAssetsCodeCompany(String companiesCode,String statusesAssetsCode) throws Exception;

	List<Assets> findByStatusesInOutCodeCompany(String companiesCode,String statusesInOutCode) throws Exception;
	
	List<Assets> findByCompaniesCode(String companiesCode) throws Exception;
	
	List<Assets> findByReqCompany(String itemsCode, String companiesCode, String statusesAssetsCode, String statusesInOutCode, Integer total) throws Exception;

	List<Assets> getExpiredAssetsCompany(String companiesCode) throws Exception;

	List<Assets> getTop5AssetsDeployCompany(String companiesCode) throws Exception;
	
	List<Assets> getNewAssetsCompany(String companiesCode)throws Exception;
	
	// end of non-admin
	
	
	//start of super-admin
	
	Assets findByAssetsName(String assetsName) throws Exception;
	
	List<Assets> findByStatAssetsInOut(String statusesAssetsCode, String statusesInOutCode) throws Exception;
	
	List<Assets> findByItemsCode(String itemsCode) throws Exception;

	List<Assets> findByBrandsCode(String brandsCode) throws Exception;

	List<Assets> findByItemsTypesCode(String itemsTypesCode) throws Exception;

	List<Assets> findByStatusesAssetsCode(String statusesAssetsCode) throws Exception;

	List<Assets> findByStatusesInOutCode(String statusesInOutCode) throws Exception;
	
	List<Assets> getExpiredAssets() throws Exception;

	List<Assets> getTop5AssetsDeploy() throws Exception;
	
	List<Assets> getNewAssets()throws Exception;
	
	List<Assets> findByReq(String itemsCode, String statusesAssetsCode, String statusesInOutCode, Integer total) throws Exception;
	
	//end of super-admin
	
	Integer countData() throws Exception;
	
	Assets saveOrUpdate(Assets assets) throws Exception;

	Boolean removeById(String id) throws Exception;
}
