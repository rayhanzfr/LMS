package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.StatusesAssets;

public interface StatusesAssetsService {
	List<StatusesAssets> findAll() throws Exception;

	StatusesAssets findById(Long id) throws Exception;

	void saveOrUpdate(StatusesAssets statusesAssets) throws Exception;

	Boolean deleteById(Long id) throws Exception;
	
}
