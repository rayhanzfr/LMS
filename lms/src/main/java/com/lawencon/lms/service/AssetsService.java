package com.lawencon.lms.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.dto.assets.GetAllAssetsDto;
import com.lawencon.lms.dto.assets.GetByIdAssetsDto;
import com.lawencon.lms.dto.assets.GetTotalAssetsReqDto;
import com.lawencon.lms.dto.assets.SaveAssetsReqDto;
import com.lawencon.lms.dto.assets.SaveAssetsResDto;
import com.lawencon.lms.dto.assets.UpdateAssetsReqDto;
import com.lawencon.lms.dto.assets.UpdateAssetsResDto;

public interface AssetsService {
	GetAllAssetsDto findAll() throws Exception;

	GetByIdAssetsDto findById(String id) throws Exception;

	GetByIdAssetsDto findByAssetsName(String assetsName) throws Exception;

	GetAllAssetsDto findByItemsCode(String itemsCode) throws Exception;

	GetAllAssetsDto findByItemsBrandsCode(String brandsCode) throws Exception;

	GetAllAssetsDto findByItemsTypesCode(String itemsTypesCode) throws Exception;

	GetAllAssetsDto findByStatusesAssetsCode(String statusesAssetsCode) throws Exception;

	GetAllAssetsDto findByStatusesInOutCode(String statusesInOutCode) throws Exception;
	
	GetTotalAssetsReqDto getTotalreq(String itemsCode, String itemsBrandsCode, String itemsTypesCode, String statusesAssetsCode, String statusesInOutCode,int total) throws Exception;

	SaveAssetsResDto save(SaveAssetsReqDto saveAssetsReqDto) throws Exception;
	
	void saveFile(MultipartFile file) throws Exception;
	
	UpdateAssetsResDto update(UpdateAssetsReqDto updateAssetsReqDto) throws Exception;
	
	UpdateAssetsResDto updateStatusAssets(UpdateAssetsReqDto updateAssetsReqDto) throws Exception;
	
	UpdateAssetsResDto updateStatusInOut(UpdateAssetsReqDto updateAssetsReqDto) throws Exception;

	Boolean removeById(String id) throws Exception;
}
