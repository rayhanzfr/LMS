package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.model.Roles;

@Repository
public class RolesDaoImpl extends BaseDaoImpl<Roles> implements RolesDao {
	
	@Override
	public Roles findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Roles findByCode(String code) throws Exception {
		Roles roles = new Roles();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT id,roles_code,roles_name,created_by,created_date,isactive,update_by,update_date,version");
			sql.append("FROM roles WHERE roles_code = :code ");
			Object result = createNativeQuery(sql.toString()).setParameter("code", code).getSingleResult();
			if (result != null) {
				roles = new Roles();
				Object[] objArr = (Object[]) result;
				roles.setId(objArr[0].toString());
				roles.setRolesCode(objArr[1].toString());
				roles.setRolesName(objArr[2].toString());
				roles.setCreatedBy(objArr[3].toString());
				roles.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
				roles.setIsActive((Boolean) objArr[5]);
				roles.setVersion((Integer)objArr[8]);
			}
		}catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Found more than one");
		}
		return roles;
	}

	@Override
	public List<Roles> findAll() throws Exception {
		return getAll();
    }


	@Override
	public Roles saveOrUpdate(Roles roles) throws Exception {
		return save(roles);
	}
	
	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
