package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.dto.statusesassets.SaveStatusesAssetsResDto;
import com.lawencon.lms.dto.statusesassets.UpdateStatusesAssetsResDto;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.StatusesAssetsService;
import com.lawencon.lms.service.UsersService;

@Service
public class StatusesAssetsServiceImpl extends BaseServiceLmsImpl implements StatusesAssetsService {

	@Autowired
	private UsersService usersService;

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

			Users users = usersService.findById(getIdAuth());

			if (users == null) {
				throw new IllegalAccessException("You need to login first!");
			}

			else if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") || users.getIsActive() == false) {
				saveRes.setMessage("only superAdmin can Insert data!");
				throw new IllegalAccessException("only superAdmin can Insert data!");
			}

			else {
				if (statusesAssets.getStatusesAssetsName() == null
						|| statusesAssets.getStatusesAssetsName().length() > 15) {
					throw new Exception("statusesAssetsName required and not longer than 15 character length");
				}

				else if (statusesAssets.getStatusesAssetsCode() == null
						|| statusesAssets.getStatusesAssetsCode().length() > 5) {
					throw new Exception("statusesAssetsCode required and not longer than 5 character length");
				}

				else {
					statusesAssets.setCreatedBy(getIdAuth());
					begin();
					statusesAssets = statusesAssetsDao.saveOrUpdate(statusesAssets);
					commit();
					saveRes.setId(statusesAssets.getId());
					saveRes.setMessage("Inserted");
				}
			}

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
			StatusesAssets statusesAssetsDb = findByCode(statusesAssets.getStatusesAssetsCode());
			statusesAssetsDb.setUpdatedBy(getIdAuth());
			statusesAssetsDb.setStatusesAssetsName(statusesAssets.getStatusesAssetsName());

			Users users = usersService.findById(getIdAuth());

			if (users == null) {
				throw new IllegalAccessException("You need to Login first!");
			}

			else if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") || users.getIsActive() == false) {
				updateRes.setMessage("only superAdmin can Update data!");
				throw new IllegalAccessException("only superAdmin can Update data!");
			}

			else {
				begin();
				statusesAssets.setUpdatedBy(getIdAuth());
				statusesAssets = statusesAssetsDao.saveOrUpdate(statusesAssetsDb);
				commit();
				updateRes.setVersion(statusesAssets.getVersion());
				updateRes.setMessage("Inserted");
			}

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
