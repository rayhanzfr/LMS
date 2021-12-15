package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.FilesDao;
import com.lawencon.lms.model.Files;
import com.lawencon.lms.service.FilesService;

public class FilesServiceImpl extends BaseServiceImpl implements FilesService {

	@Autowired
	private FilesDao filesDao;

	@Override
	public Files save(Files files) throws Exception {
		try {
			begin();
			files = filesDao.saveOrUpdate(files);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return files;
	}

	@Override
	public Files update(Files files) throws Exception {
		try {
			Files filesDb = findById(files.getId());	
			files.setCreatedAt(filesDb.getCreatedAt());
			files.setCreatedBy(filesDb.getCreatedBy());

			begin();
			files = filesDao.saveOrUpdate(files);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return files;
	}

	@Override
	public Files findById(String id) throws Exception {
		return filesDao.findById(id);
	}

	@Override
	public List<Files> findAll() throws Exception {
		return filesDao.findAll();
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = filesDao.removeById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
