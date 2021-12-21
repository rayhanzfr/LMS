package com.lawencon.lms.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.StatusesInOutDao;
import com.lawencon.lms.dto.statusesinout.SaveStatusesInOutResDto;
import com.lawencon.lms.dto.statusesinout.UpdateStatusesInOutResDto;
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.StatusesInOutService;
import com.lawencon.lms.service.UsersService;

@Service
public class StatusesInOutServiceImpl extends BaseServiceLmsImpl implements StatusesInOutService {

	@Autowired
	private StatusesInOutDao statusesInOutDao;
	

	@Autowired
	private UsersService usersService;
	
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
			Users users = usersService.findById(getIdAuth());
			if(users==null) {
				throw new IllegalAccessException("must login first");
			}
			else if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") && users.getIsActive() == false) {
				throw new IllegalAccessException("only superAdmin can Insert data!");
			}
			else {
				begin();
				statusesInOut = statusesInOutDao.saveOrUpdate(statusesInOut);
				commit();
				resDto.setId(statusesInOut.getId());
				resDto.setMesssage("INSERTED");
			}
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
			statusesInAndOut.setUpdatedBy(getIdAuth());
			statusesInAndOut.setStatusesInOutName(statusesInOut.getStatusesInOutName());
			
			begin();
			statusesInOut = statusesInOutDao.saveOrUpdate(statusesInAndOut);
			commit();
			resDto.setVersion(statusesInOut.getVersion());
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
