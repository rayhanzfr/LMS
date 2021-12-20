package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dto.permissions.SavePermissionsResDto;
import com.lawencon.lms.dto.permissions.UpdatePermissionsResDto;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.service.PermissionsService;

@Service
public class PermissionsServiceImpl extends BaseServiceLmsImpl implements PermissionsService {

	@Autowired
	private PermissionsDao permissionsDao;

	@Override
	public SavePermissionsResDto save(Permissions permissions) throws Exception {
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

	@Override
	public UpdatePermissionsResDto update(Permissions permissions) throws Exception {
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

	@Override
	public Permissions findById(String id) throws Exception {
		return permissionsDao.findById(id);
	}

	@Override
	public List<Permissions> findAll() throws Exception {
		return permissionsDao.findAll();
	}

	@Override
	public Permissions findByCode(String code) throws Exception {
		return permissionsDao.findByCode(code);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
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

	public String generateCode() throws Exception {
		Integer increment = permissionsDao.countData() + 1;
		String code = EnumCode.PERMISSIONS.getCode() + increment;
		return code;
	}
}
