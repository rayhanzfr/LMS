package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.StatusesAssets;

public interface StatusesAssetsDao {

	List<StatusesAssets> findAll() throws Exception;

	StatusesAssets findById(String id) throws Exception;

	StatusesAssets findByCode(String code) throws Exception;
	
	StatusesAssets saveOrUpdate(StatusesAssets statusesAssets) throws Exception;

	Boolean removeById(String id) throws Exception;
}
