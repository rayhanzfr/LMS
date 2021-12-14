package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Assets;

public interface AssetsDao {
	List<Assets> findAll() throws Exception;
	Assets findById(String id) throws Exception;
	Assets findByCode(String code) throws Exception;
	Assets saveOrUpdate(Assets assets) throws Exception;
	Boolean removeById(String id) throws Exception;
}
