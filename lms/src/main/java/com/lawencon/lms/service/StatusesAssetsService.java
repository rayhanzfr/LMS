package com.lawencon.lms.service;

import com.lawencon.lms.model.StatusesAssets;

public interface StatusesAssetsService {
	void getAll() throws Exception;

	void getById(Long id) throws Exception;

	StatusesAssets insert(StatusesAssets statusesAssets) throws Exception;

	StatusesAssets update(StatusesAssets statusesAssets) throws Exception;

	void deleteById(Long id) throws Exception;
	
}
