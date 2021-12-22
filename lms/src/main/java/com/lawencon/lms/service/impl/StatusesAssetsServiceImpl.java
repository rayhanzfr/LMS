package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.statusesassets.SaveStatusesAssetsResDto;
import com.lawencon.lms.dto.statusesassets.UpdateStatusesAssetsResDto;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.StatusesAssetsService;

@Service
public class StatusesAssetsServiceImpl extends BaseServiceLmsImpl implements StatusesAssetsService {

	@Autowired
	private StatusesAssetsDao statusesAssetsDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Override
	public List<StatusesAssets> findAll() throws Exception {
		
		String permissionsCode = "PERMSN45";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return statusesAssetsDao.findAll();
		throw new Exception("Access Denied");
	}

	@Override
	public StatusesAssets findById(String id) throws Exception {

		String permissionsCode = "PERMSN45";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return statusesAssetsDao.findById(id);
		throw new Exception("Access Denied");
	}

	@Override
	public StatusesAssets findByCode(String code) throws Exception {

		String permissionsCode = "PERMSN45";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return statusesAssetsDao.findByCode(code);
		throw new Exception("Access Denied");
	}

	@Override
	public SaveStatusesAssetsResDto save(StatusesAssets statusesAssets) throws Exception {
		SaveStatusesAssetsResDto saveRes = new SaveStatusesAssetsResDto();

		try {
			String permissionsCode = "PERMSN46";
			Boolean validation = validationUsers(permissionsCode);
			if (!validation) {
				throw new Exception("Access Denied");
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

			String permissionsCode = "PERMSN47";
			Boolean validation = validationUsers(permissionsCode);
			if (!validation) {
				throw new Exception("Access Denied");
			}

			else {
				if (statusesAssetsDb.getStatusesAssetsName() == null
						|| statusesAssetsDb.getStatusesAssetsName().length() > 15) {
					throw new Exception("statusesAssetsName required and not longer than 15 character length");
				}
				
				else {
					begin();
					statusesAssets.setUpdatedBy(getIdAuth());
					statusesAssets = statusesAssetsDao.saveOrUpdate(statusesAssetsDb);
					commit();
					updateRes.setVersion(statusesAssets.getVersion());
					updateRes.setMessage("Inserted");
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateRes;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		String permissionsCode = "PERMSN48";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			try {
				begin();
				boolean isDeleted = statusesAssetsDao.removeById(id);
				commit();
				return isDeleted;
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
				throw new Exception(e);
			}
		}

		throw new Exception("Access Denied");
	}

	public Boolean validationUsers(String permissionsCode) throws Exception {
		try {
			Users users = usersDao.findById(getIdAuth());
			Roles roles = rolesDao.findById(users.getRoles().getId());
			Permissions permissions = permissionsDao.findByCode(permissionsCode);
			List<PermissionsRoles> listPermissionsRoles = permissionsRolesDao.findAll();
			for (int i = 0; i < listPermissionsRoles.size(); i++) {
				if (listPermissionsRoles.get(i).getPermissions().getId().equals(permissions.getId())) {
					if (listPermissionsRoles.get(i).getRoles().getId().equals(roles.getId())) {
						return true;
					}
				}
			}
			return false;
		} catch (NotFoundException e) {
			throw new Exception(e);
		}
	}

}
