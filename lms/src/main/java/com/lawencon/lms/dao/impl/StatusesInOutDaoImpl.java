package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.StatusesInOutDao;
import com.lawencon.lms.model.StatusesInOut;

@Repository
public class StatusesInOutDaoImpl extends BaseDaoImpl<StatusesInOut> implements StatusesInOutDao {

	@Override
	public List<StatusesInOut> findAll() throws Exception {
		return getAll();
	}

	@Override
	public StatusesInOut findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public StatusesInOut saveOrUpdate(StatusesInOut statusesInOut) throws Exception {
		return save(statusesInOut);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public StatusesInOut findByCode(String code) throws Exception {
		StatusesInOut statusesInOut = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					" SELECT id, statuses_in_out_code, statuses_in_out_name, created_by, created_at, updated_by, updated_at, version ");
			sql.append(" FROM statuses_in_out ");
			Object resultQuery = createNativeQuery(sql.toString()).getSingleResult();
			if (resultQuery != null) {
				Object[] obj = (Object[]) resultQuery;
				statusesInOut = new StatusesInOut();
				statusesInOut.setId(obj[0].toString());
				statusesInOut.setStatusesInOutCode(obj[1].toString());
				statusesInOut.setStatusesInOutName(obj[2].toString());
				statusesInOut.setCreatedBy(obj[3].toString());
				statusesInOut.setCreatedAt(Timestamp.valueOf(obj[4].toString()).toLocalDateTime());

				if (obj[5] != null) {
					statusesInOut.setUpdatedBy(obj[5].toString());
				}
				if (obj[6] != null) {
					statusesInOut.setUpdatedAt(Timestamp.valueOf(obj[6].toString()).toLocalDateTime());
				}
				statusesInOut.setVersion(Integer.valueOf(obj[7].toString()));
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NoResultException("Found more than one");
		}
		return statusesInOut;
	}

}
