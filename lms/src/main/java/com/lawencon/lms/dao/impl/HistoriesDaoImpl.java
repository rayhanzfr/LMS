package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.HistoriesDao;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Histories;
import com.lawencon.lms.model.Users;

@Repository
public class HistoriesDaoImpl extends BaseDaoImpl<Histories> implements HistoriesDao {

	@Override
	public Histories findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<Histories> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Histories saveOrUpdate(Histories histories) throws Exception {
		return save(histories);
	}

	@Override
	public List<Histories> findByUsersId(String usersId) throws Exception {
		List<Histories> listHistories = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT h.id,h.users_id,h.assets_id,h.activity_name,h.created_by");
			sql.append("FROM histories as h");
			sql.append("INNER JOIN users as u ON u.id = h.users_id");
			sql.append("INNER JOIN assets as a ON a.id = h.assets_id");
			sql.append("WHERE h.users_id = :usersId");
			Object result = createNativeQuery(sql.toString()).setParameter("usersId", usersId).getSingleResult();
			if (result != null) {
				Histories histories = new Histories();
				Object[] objArr = (Object[]) result;
				histories.setId(objArr[0].toString());
				Users users = new Users();
				users.setId(objArr[1].toString());
				histories.setUsers(users);
				Assets assets = new Assets();
				assets.setId(objArr[2].toString());
				histories.setAssets(assets);
				histories.setActivityName(objArr[3].toString());
				histories.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
				listHistories.add(histories);
			}
		} catch (
			NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Found more than one");
		}
		return listHistories;
	}

}
