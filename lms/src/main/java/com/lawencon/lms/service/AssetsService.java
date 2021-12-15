package com.lawencon.lms.service;

import com.lawencon.lms.dto.assets.GetAllAssetsDto;
import com.lawencon.lms.dto.assets.GetByIdAssetsDto;
import com.lawencon.lms.dto.assets.SaveAssetsReqDto;
import com.lawencon.lms.dto.assets.SaveAssetsResDto;
import com.lawencon.lms.dto.assets.UpdateAssetsReqDto;
import com.lawencon.lms.dto.assets.UpdateAssetsResDto;

public interface AssetsService {
	GetAllAssetsDto findAll() throws Exception;

	GetByIdAssetsDto findById(String id) throws Exception;

	GetByIdAssetsDto findByAssetsName(String assetsName) throws Exception;

	GetAllAssetsDto findByItemsCode(String itemsCode) throws Exception;

	GetAllAssetsDto findByBrandsCode(String brandsCode) throws Exception;

	GetAllAssetsDto findByItemsTypesCode(String itemsTypesCode) throws Exception;

	GetAllAssetsDto findByStatusesAssetsCode(String statusesAssetsCode) throws Exception;

	GetAllAssetsDto findByStatusesInOutCode(String statusesInOutCode) throws Exception;

	SaveAssetsResDto save(SaveAssetsReqDto saveAssetsReqDto) throws Exception;
	
	UpdateAssetsResDto update(UpdateAssetsReqDto updateAssetsReqDto) throws Exception;
	
	UpdateAssetsResDto updateStatusAssets(UpdateAssetsReqDto updateAssetsReqDto) throws Exception;
	
	UpdateAssetsResDto updateStatusInOut(UpdateAssetsReqDto updateAssetsReqDto) throws Exception;

	Boolean removeById(String id) throws Exception;
}
