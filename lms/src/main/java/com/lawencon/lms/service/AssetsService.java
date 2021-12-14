package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.model.Assets;

public interface AssetsService {
	List<Assets> findAll() throws Exception;
	Assets findById() throws Exception;
	void saveOrUpdate(Assets assets) throws Exception;
	Boolean deleteById() throws Exception;
}
