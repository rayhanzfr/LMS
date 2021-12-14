package com.lawencon.lms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.model.Roles;

@Repository
public class RolesDaoImpl extends BaseDaoImpl<Roles> implements RolesDao {


@Override
public Roles findById(String id) throws Exception {
	return findByCode(id);
}

	@Override
	public Roles findByCode(String code) throws Exception {
	Roles roles = createQuery("FROM Roles WHERE code = ? ", Roles.class)
        .setParameter(1, code)
        .getSingleResult();
    return roles;
  }

	@Override
	public List<Roles> findAll() throws Exception {
		return findAll();
    }


	@Override
	public Roles saveOrUpdate(Roles roles) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Boolean removeById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
