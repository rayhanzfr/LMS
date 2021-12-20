package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dto.roles.SaveRolesResDto;
import com.lawencon.lms.dto.roles.UpdateRolesResDto;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.service.RolesService;

@Service
public class RolesServiceImpl extends BaseServiceLmsImpl implements RolesService {

	@Autowired
	private RolesDao rolesDao;

	@Override
	public SaveRolesResDto save(Roles roles) throws Exception {
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

	@Override
	public UpdateRolesResDto update(Roles roles) throws Exception {
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

	@Override
	public Roles findById(String id) throws Exception {
		return rolesDao.findById(id);
	}

	@Override
	public List<Roles> findAll() throws Exception {
		return rolesDao.findAll();
	}

	@Override
	public Roles findByCode(String code) throws Exception {
		return rolesDao.findByCode(code);
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
	
	public String generateCode()throws Exception{
		Integer increment = rolesDao.countData()+1;
		String code= EnumCode.ROLES.getCode()+increment;
		return code;
	}
}
