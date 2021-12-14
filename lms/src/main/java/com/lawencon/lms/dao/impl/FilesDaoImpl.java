package com.lawencon.lms.dao.impl;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.FilesDao;
import com.lawencon.lms.model.Files;

public class FilesDaoImpl extends BaseDaoImpl<Files> implements FilesDao {

	@Override
	public Files findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<Files> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Files saveOrUpdate(Files files) throws Exception {
		return save(files);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}
