package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.StatusesInOutDao;
import com.lawencon.lms.dto.statusesinout.SaveStatusesInOutResDto;
import com.lawencon.lms.dto.statusesinout.UpdateStatusesInOutResDto;
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.service.StatusesInOutService;

@Service
public class StatusesInOutServiceImpl extends BaseServiceLmsImpl implements StatusesInOutService {

	@Autowired
	private StatusesInOutDao statusesInOutDao;
	
	
	@Override
	public List<StatusesInOut> findAll() throws Exception {
		return statusesInOutDao.findAll();
	}

	@Override
	public StatusesInOut findById(String id) throws Exception {
		return statusesInOutDao.findById(id);
	}

	@Override
	public StatusesInOut findByCode(String code) throws Exception {
		return statusesInOutDao.findByCode(code);
	}

	@Override
	public SaveStatusesInOutResDto save(StatusesInOut statusesInOut) throws Exception {
		SaveStatusesInOutResDto resDto = new SaveStatusesInOutResDto();
		try {
			begin();
			statusesInOut = statusesInOutDao.saveOrUpdate(statusesInOut);
			commit();
			resDto.setId(statusesInOut.getId());
			resDto.setMesssage("INSERTED");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return resDto;
	}

	@Override
	public UpdateStatusesInOutResDto update(StatusesInOut statusesInOut) throws Exception {
		UpdateStatusesInOutResDto resDto = new UpdateStatusesInOutResDto();
		try {
			StatusesInOut statusesInAndOut = statusesInOutDao.findByCode(statusesInOut.getStatusesInOutCode());
			statusesInOut.setCreatedBy(statusesInAndOut.getCreatedBy());
			statusesInOut.setCreatedAt(statusesInAndOut.getCreatedAt());
			
			begin();
			statusesInOut = statusesInOutDao.saveOrUpdate(statusesInOut);
			commit();
			resDto.setVersion(statusesInAndOut.getVersion());
			resDto.setMessage("UPDATED");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return resDto;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		try {
			begin();
			boolean delete = statusesInOutDao.removeById(id);
			commit();
			
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

}
