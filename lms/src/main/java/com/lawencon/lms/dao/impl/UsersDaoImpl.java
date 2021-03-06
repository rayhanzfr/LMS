package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

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
			sql.append(" SELECT u.id, r.roles_code, u.users_email,u.users_password, u.created_by, u.created_at, u.version ");
			sql.append(" FROM users as u ");
			sql.append(" INNER JOIN roles as r ON r.id = u.roles_id ");
			sql.append(" WHERE u.users_email = :email ");

			Object resultQuery = createNativeQuery(sql.toString()).setParameter("email", email).getSingleResult();
			if (resultQuery != null) {
				Object[] obj = (Object[]) resultQuery;
				user = new Users();
				user.setId(obj[0].toString());

				Roles roles = new Roles();
				roles.setRolesCode(obj[1].toString());
				user.setRoles(roles);

				user.setUsersEmail(obj[2].toString());
				user.setUsersPassword(obj[3].toString());
				user.setCreatedBy(obj[4].toString());
				user.setCreatedAt(Timestamp.valueOf(obj[5].toString()).toLocalDateTime());
				user.setVersion(Integer.valueOf(obj[6].toString()));
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NoResultException("Found more than one");
		}
		return user;
	}

	@Override
	public Users saveOrUpdate(Users users) throws Exception {
		return save(users);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public List<Users> findByCompany(String companiesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT u.id, r.roles_code, u.users_email,u.users_password ");
		sql.append(" FROM users as u ");
		sql.append(" INNER JOIN employees e ON e.users_id = u.id ");
		sql.append(" INNER JOIN companies c ON e.companies_id = c.id ");
		sql.append(" INNER JOIN roles as r ON r.id = u.roles_id ");
		sql.append(" WHERE c.companies_code = :companiesCode ");

		List<?> resultQuery = createNativeQuery(sql.toString()).setParameter("companiesCode", companiesCode).getResultList();
		List<Users> listUser = new ArrayList<Users>();
		resultQuery.forEach(rs -> {
			Object[] obj = (Object[]) rs;
			
			Users user = new Users();
			user.setId(obj[0].toString());

			Roles roles = new Roles();
			roles.setRolesCode(obj[1].toString());
			user.setRoles(roles);

			user.setUsersEmail(obj[2].toString());
			user.setUsersPassword(obj[3].toString());
			listUser.add(user);
		});
		return listUser;
	}
}