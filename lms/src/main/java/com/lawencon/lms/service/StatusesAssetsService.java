package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.statusesassets.SaveStatusesAssetsResDto;
import com.lawencon.lms.dto.statusesassets.UpdateStatusesAssetsResDto;
import com.lawencon.lms.model.StatusesAssets;

public interface StatusesAssetsService {
	List<StatusesAssets> findAll() throws Exception;

	StatusesAssets findById(String id) throws Exception;

	StatusesAssets findByCode(String code) throws Exception;
	
	SaveStatusesAssetsResDto save(StatusesAssets statusesAssets) throws Exception;

	UpdateStatusesAssetsResDto update(StatusesAssets statusesAssets) throws Exception;
	
	Boolean removeById(String id) throws Exception;
	
}
