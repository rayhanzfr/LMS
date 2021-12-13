package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.StatusesAssets;

public interface StatusesAssetsDao {

	List<StatusesAssets> getAll() throws Exception;

	StatusesAssets getById(String id) throws Exception;

	StatusesAssets getByCode(String code) throws Exception;
	
	StatusesAssets saveOrUpdate(StatusesAssets statusesAssets) throws Exception;

	Boolean deleteById(String id) throws Exception;
}
