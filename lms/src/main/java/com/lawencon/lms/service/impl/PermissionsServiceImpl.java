package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.permissions.SavePermissionsResDto;
import com.lawencon.lms.dto.permissions.UpdatePermissionsResDto;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.PermissionsService;

@Service
public class PermissionsServiceImpl extends BaseServiceLmsImpl implements PermissionsService {

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Override
	public SavePermissionsResDto save(Permissions permissions) throws Exception {
		String permissionsCode = "PERMSN6";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			SavePermissionsResDto savePermissionsResDto = new SavePermissionsResDto();
			try {
				begin();
				permissions.setPermissionsCode(generateCode());
				permissions.setCreatedBy(getIdAuth());
				permissions = permissionsDao.saveOrUpdate(permissions);
				commit();
				savePermissionsResDto.setId(permissions.getId());
				savePermissionsResDto.setMsg("OK");
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return savePermissionsResDto;
		}
		throw new Exception("Access Denied");

	}

	@Override
	public UpdatePermissionsResDto update(Permissions permissions) throws Exception {
		String permissionsCode = "PERMSN7";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			UpdatePermissionsResDto updatePermissionsResDto = new UpdatePermissionsResDto();
			try {
				Permissions permissionsDb = findByCode(permissions.getPermissionsCode());
				permissions.setCreatedAt(permissionsDb.getCreatedAt());
				permissions.setCreatedBy(permissionsDb.getCreatedBy());
				permissions.setUpdatedBy(getIdAuth());
				begin();
				permissions = permissionsDao.saveOrUpdate(permissions);
				commit();
				updatePermissionsResDto.setVersion(permissions.getVersion());
				updatePermissionsResDto.setMsg("OK");
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return updatePermissionsResDto;
		}
		throw new Exception("Access Denied");
	}

	@Override
	public Permissions findById(String id) throws Exception {
		String permissionsCode = "PERMSN5";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return permissionsDao.findById(id);
		throw new Exception("Access Denied");
	}

	@Override
	public List<Permissions> findAll() throws Exception {
		String permissionsCode = "PERMSN5";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return permissionsDao.findAll();
		throw new Exception("Access Denied");

	}

	@Override
	public Permissions findByCode(String code) throws Exception {
		String permissionsCode = "PERMSN5";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return permissionsDao.findByCode(code);
		throw new Exception("Access Denied");
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		String permissionsCode = "PERMSN8";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			try {
				begin();
				boolean isDeleted = permissionsDao.removeById(id);
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

	public String generateCode() throws Exception {
		Integer increment = permissionsDao.countData() + 1;
		String code = EnumCode.PERMISSIONS.getCode() + increment;
		return code;
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
