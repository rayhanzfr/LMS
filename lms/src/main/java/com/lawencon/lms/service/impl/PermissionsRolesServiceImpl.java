package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dto.permissionsroles.SavePermissionsRolesResDto;
import com.lawencon.lms.dto.permissionsroles.UpdatePermissionsRolesResDto;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.service.PermissionsRolesService;
import com.lawencon.lms.service.PermissionsService;
import com.lawencon.lms.service.RolesService;

@Service
public class PermissionsRolesServiceImpl extends BaseServiceLmsImpl implements PermissionsRolesService {

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Autowired
	private PermissionsService permissionsService;

	@Autowired
	private RolesService rolesService;

	@Override
	public SavePermissionsRolesResDto save(PermissionsRoles permissionsRoles) throws Exception {
		SavePermissionsRolesResDto savePermissionsRolesResDto = new SavePermissionsRolesResDto();
		try {
			Permissions permissions = permissionsService.findByCode(permissionsRoles.getPermissions().getPermissionsCode());
			Roles roles = rolesService.findByCode(permissionsRoles.getRoles().getRolesCode());
			permissionsRoles.setPermissions(permissions);
			permissionsRoles.setRoles(roles);
			permissionsRoles.setCreatedBy(getIdAuth());
			begin();
			permissionsRoles = permissionsRolesDao.saveOrUpdate(permissionsRoles);
			commit();
			savePermissionsRolesResDto.setId(permissionsRoles.getId());
			savePermissionsRolesResDto.setMsg("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return savePermissionsRolesResDto;
	}

	@Override
	public UpdatePermissionsRolesResDto update(PermissionsRoles permissionsRoles) throws Exception {
		UpdatePermissionsRolesResDto updatePermissionsRolesResDto = new UpdatePermissionsRolesResDto();
		try {
			Permissions permissions = permissionsService.findByCode(permissionsRoles.getPermissions().getPermissionsCode());
			Roles roles = rolesService.findByCode(permissionsRoles.getRoles().getRolesCode());
			permissionsRoles.setPermissions(permissions);
			permissionsRoles.setRoles(roles);
			PermissionsRoles permissionsRolesDb = findById(permissionsRoles.getId());
			permissionsRoles.setCreatedAt(permissionsRolesDb.getCreatedAt());
			permissionsRoles.setCreatedBy(permissionsRolesDb.getCreatedBy());
			permissionsRoles.setUpdatedBy(getIdAuth());
			begin();
			permissionsRoles = permissionsRolesDao.saveOrUpdate(permissionsRoles);
			commit();
			updatePermissionsRolesResDto.setVersion(permissionsRoles.getVersion());
			updatePermissionsRolesResDto.setMsg("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updatePermissionsRolesResDto;
	}

	@Override
	public PermissionsRoles findById(String id) throws Exception {
		return permissionsRolesDao.findById(id);
	}

	@Override
	public List<PermissionsRoles> findAll() throws Exception {
		return permissionsRolesDao.findAll();
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = permissionsRolesDao.removeById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
