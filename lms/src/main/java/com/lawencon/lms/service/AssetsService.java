package com.lawencon.lms.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.dto.assets.GetAllAssetsDto;
import com.lawencon.lms.dto.assets.GetByIdAssetsDto;
import com.lawencon.lms.dto.assets.GetTotalAssetsReqDto;
import com.lawencon.lms.dto.assets.SaveAssetsReqDto;
import com.lawencon.lms.dto.assets.SaveAssetsResDto;
import com.lawencon.lms.dto.assets.UpdateAssetsReqDto;
import com.lawencon.lms.dto.assets.UpdateAssetsResDto;
import com.lawencon.lms.model.Assets;

public interface AssetsService {
	GetAllAssetsDto findAll() throws Exception;

	GetByIdAssetsDto findById(String id) throws Exception;

	GetByIdAssetsDto findByAssetsName(String assetsName) throws Exception;

	GetAllAssetsDto findByItemsCode(String itemsCode) throws Exception;

	GetAllAssetsDto findByItemsBrandsCode(String brandsCode) throws Exception;

	GetAllAssetsDto findByItemsTypesCode(String itemsTypesCode) throws Exception;

	GetAllAssetsDto findByStatusesAssetsCode(String statusesAssetsCode) throws Exception;

	GetAllAssetsDto findByStatusesInOutCode(String statusesInOutCode) throws Exception;
	
	List<Assets> getTotalreq(String itemsCode,String statusesAssetsCode,int total) throws Exception;

	SaveAssetsResDto save(SaveAssetsReqDto saveAssetsReqDto) throws Exception;
	
	void saveFile(MultipartFile file) throws Exception;
	
	UpdateAssetsResDto update(UpdateAssetsReqDto updateAssetsReqDto) throws Exception;
	
	Map<String,Object> getAssetsExpired()throws Exception;
	
	GetAllAssetsDto getTop5AssetsDeploy() throws Exception;
	
	GetAllAssetsDto getNewAssets()throws Exception;

	Boolean removeById(String id) throws Exception;
}
