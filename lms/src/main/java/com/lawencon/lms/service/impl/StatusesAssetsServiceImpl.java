package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.dto.statusesassets.SaveStatusesAssetsResDto;
import com.lawencon.lms.dto.statusesassets.UpdateStatusesAssetsResDto;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.service.StatusesAssetsService;

public class StatusesAssetsServiceImpl extends BaseServiceImpl implements StatusesAssetsService {

	@Autowired
	private StatusesAssetsDao statusesAssetsDao;

	@Override
	public List<StatusesAssets> findAll() throws Exception {
		return statusesAssetsDao.findAll();
	}

	@Override
	public StatusesAssets findById(String id) throws Exception {
		return statusesAssetsDao.findById(id);
	}

	@Override
	public StatusesAssets findByCode(String code) throws Exception {
		return statusesAssetsDao.findByCode(code);
	}

	@Override
	public SaveStatusesAssetsResDto save(StatusesAssets statusesAssets) throws Exception {
		SaveStatusesAssetsResDto saveRes = new SaveStatusesAssetsResDto();
		
		try {
			begin();
			statusesAssets.setStatusesAssetsCode(null);
			statusesAssets = statusesAssetsDao.saveOrUpdate(statusesAssets);
			commit();
			
			saveRes.setId(statusesAssets.getId());
			saveRes.setMessage("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return saveRes;
	}

	@Override
	public UpdateStatusesAssetsResDto update(StatusesAssets statusesAssets) throws Exception {
		UpdateStatusesAssetsResDto updateRes = new UpdateStatusesAssetsResDto();
		
		try {
			StatusesAssets statusesAssetsDb = findById(statusesAssets.getId());	
			statusesAssets.setCreatedAt(statusesAssetsDb.getCreatedAt());
			statusesAssets.setCreatedBy(statusesAssetsDb.getCreatedBy());

			begin();
			statusesAssets = statusesAssetsDao.saveOrUpdate(statusesAssets);
			commit();
			
			updateRes.setVersion(statusesAssets.getVersion());
			updateRes.setMessage("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateRes;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return statusesAssetsDao.removeById(id);
	}

}
