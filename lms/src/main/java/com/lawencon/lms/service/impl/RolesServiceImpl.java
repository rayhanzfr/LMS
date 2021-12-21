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
import com.lawencon.lms.dto.roles.SaveRolesResDto;
import com.lawencon.lms.dto.roles.UpdateRolesResDto;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.RolesService;

@Service
public class RolesServiceImpl extends BaseServiceLmsImpl implements RolesService {

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Override
	public SaveRolesResDto save(Roles roles) throws Exception {
		String permissionsCode = "PERMSN2";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			SaveRolesResDto saveRolesResDto = new SaveRolesResDto();
			try {
				begin();
				roles.setCreatedBy(getIdAuth());
				roles.setRolesCode(generateCode());
				roles = rolesDao.saveOrUpdate(roles);
				commit();
				saveRolesResDto.setId(roles.getId());
				saveRolesResDto.setMsg("OK");
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return saveRolesResDto;
		}
		throw new Exception("Access Denied");
	}

	@Override
	public UpdateRolesResDto update(Roles roles) throws Exception {
		String permissionsCode = "PERMSN3";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			UpdateRolesResDto updateRolesResDto = new UpdateRolesResDto();
			try {
				Roles rolesDb = findByCode(roles.getRolesCode());
				roles.setCreatedAt(rolesDb.getCreatedAt());
				roles.setCreatedBy(rolesDb.getCreatedBy());
				roles.setUpdatedBy(getIdAuth());

				begin();
				roles = rolesDao.saveOrUpdate(roles);
				commit();
				updateRolesResDto.setVersion(roles.getVersion());
				updateRolesResDto.setMsg("OK");
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return updateRolesResDto;
		}
		throw new Exception("Access Denied");
	}

	@Override
	public Roles findById(String id) throws Exception {
		String permissionsCode = "PERMSN1";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return rolesDao.findById(id);
		throw new Exception("Access Denied");
	}

	@Override
	public List<Roles> findAll() throws Exception {
		String permissionsCode = "PERMSN1";
		validationUsers(permissionsCode);
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return rolesDao.findAll();
		throw new Exception("Access Denied");
	}

	@Override
	public Roles findByCode(String code) throws Exception {
		String permissionsCode = "PERMSN1";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return rolesDao.findByCode(code);
		throw new Exception("Access Denied");
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = rolesDao.removeById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public String generateCode() throws Exception {
		Integer increment = rolesDao.countData() + 1;
		String code = EnumCode.ROLES.getCode() + increment;
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
