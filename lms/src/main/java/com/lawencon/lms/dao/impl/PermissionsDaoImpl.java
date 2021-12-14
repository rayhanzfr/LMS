package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.model.Permissions;

public class PermissionsDaoImpl extends BaseDaoImpl<Permissions> implements PermissionsDao {


	@Override
	public Permissions findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Permissions findByCode(String code) throws Exception {
		Permissions permissions = new Permissions();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT id,permissions_code,permissions_name,created_by,created_date,isactive,update_by,update_date,version");
			sql.append("FROM permissions WHERE permissions_code = :code ");
			Object result = createNativeQuery(sql.toString()).setParameter("code", code).getSingleResult();
			if (result != null) {
				permissions = new Permissions();
				Object[] objArr = (Object[]) result;
				permissions.setId(objArr[0].toString());
				permissions.setPermissionsCode(objArr[1].toString());
				permissions.setPermissionsName(objArr[2].toString());
				permissions.setCreatedBy(objArr[3].toString());
				permissions.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
				permissions.setIsActive((Boolean) objArr[5]);
				permissions.setVersion(Long.valueOf(objArr[8].toString()));
			}
		}catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Found more than one");
		}
		return permissions;
  }

	@Override
	public List<Permissions> findAll() throws Exception {
		return getAll();
    }


	@Override
	public Permissions saveOrUpdate(Permissions permissions) throws Exception {
		return save(permissions);
	}
	
	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}
