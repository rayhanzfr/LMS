package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;

@Repository
public class UsersDaoImpl extends BaseDaoImpl<Users> implements UsersDao {

	@Override
	public List<Users> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Users findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Users findByEmail(String email) throws Exception {
		Users user = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT u.id r.roles_name, u.users_email, u.created_by, u.created_at, u.version ");
			sql.append(" FROM users as u ");
			sql.append(" INNER JOIN roles as r ON r.id = u.roles_id ");
			sql.append(" WHERE u.email = :email ");

			Object resultQuery = createNativeQuery(sql.toString()).setParameter("email", email).getSingleResult();
			if (resultQuery != null) {
				Object[] obj = (Object[]) resultQuery;
				user = new Users();
				user.setId(obj[0].toString());

				Roles roles = new Roles();
				roles.setRolesName(obj[1].toString());
				user.setRoles(roles);

				user.setUsersEmail(obj[2].toString());
				user.setCreatedBy(obj[3].toString());
				user.setCreatedAt(Timestamp.valueOf(obj[4].toString()).toLocalDateTime());
				user.setVersion(Integer.valueOf(obj[5].toString()));
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Users saveOrUpdate(Users users) throws Exception {
		return save(users);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return removeById(id);
	}

}
