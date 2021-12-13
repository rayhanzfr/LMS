package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Assets;

public interface AssetsDao {
	List<Assets> getAll() throws Exception;
	Assets getById(String id) throws Exception;
	Assets getByCode(String code) throws Exception;
	Assets saveOrUpdate(Assets assets) throws Exception;
	Boolean removeById(String id) throws Exception;
}
