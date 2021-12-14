package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.StatusesAssets;

public interface StatusesAssetsService {
	List<StatusesAssets> findAll() throws Exception;

	StatusesAssets findById(String id) throws Exception;

	StatusesAssets findByCode(String code) throws Exception;
	
	StatusesAssets save(StatusesAssets statusesAssets) throws Exception;

	StatusesAssets update(StatusesAssets statusesAssets) throws Exception;
	
	Boolean removeById(String id) throws Exception;
	
}
