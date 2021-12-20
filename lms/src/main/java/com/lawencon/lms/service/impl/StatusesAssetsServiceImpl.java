package com.lawencon.lms.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.statusesassets.SaveStatusesAssetsResDto;
import com.lawencon.lms.dto.statusesassets.UpdateStatusesAssetsResDto;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.StatusesAssetsService;
import com.lawencon.lms.service.UsersService;

@Service
public class StatusesAssetsServiceImpl extends BaseServiceLmsImpl implements StatusesAssetsService {

	@Autowired
	private UsersDao usersDao;

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

			
			Users users = usersDao.findById("80d0a70b-feee-4292-870b-ccec44bb064d");
			if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") && users.getIsActive() == false) {
				throw new IllegalAccessException("only superAdmin can Insert data!");
			}

			begin();
			statusesAssets.setCreatedBy(getIdAuth());
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
			statusesAssetsDb.setUpdatedAt(LocalDateTime.now());
			statusesAssetsDb.setUpdatedBy(getIdAuth());
			statusesAssetsDb.setStatusesAssetsName(statusesAssets.getStatusesAssetsName());

			Users users = new Users();
			users = usersDao.findById(getIdAuth());
			if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") && users.getIsActive() == false) {
				throw new IllegalAccessException("only superAdmin can Update data!");
			}
			
			begin();
			statusesAssets = statusesAssetsDao.saveOrUpdate(statusesAssetsDb);
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
