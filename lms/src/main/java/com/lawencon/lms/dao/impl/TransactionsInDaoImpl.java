package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.TransactionsInDao;
import com.lawencon.lms.model.TransactionsIn;
import com.lawencon.lms.model.TransactionsOut;

public class TransactionsInDaoImpl extends BaseDaoImpl<TransactionsIn> implements TransactionsInDao {

	@Override
	public List<TransactionsIn> findAll() throws Exception {
		return getAll();
	}

	@Override
	public TransactionsIn findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public TransactionsIn findByCode(String code) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT tin.id, tin.transactions_in_code, tin.transactions_in_date, tout.id, tout.trasanctions_out_code, tin.version, tin.created_at, tin.created_by, tin.updated_at, tin.updated_by, tin.is_active");
		sql.append(" FROM transactions_in as tin ");
		sql.append(" INNER JOIN transactions_out as tout ON tout.id = tin.transactions_out_id ");
		sql.append(" WHERE tin.transactions_in_code = :transactions_in_code ");

		TransactionsIn transactionsIn = null;

		try {
			Object resultQuery = createNativeQuery(sql.toString()).setParameter("locations_code", code)
					.getSingleResult();

			if (resultQuery != null) {
				Object[] objArr = (Object[]) resultQuery;
				transactionsIn = new TransactionsIn();

				String tinId = objArr[0].toString();
				String tinCode = objArr[1].toString();
				LocalDateTime tinDate = Timestamp.valueOf(objArr[2].toString()).toLocalDateTime();
				String toutId = objArr[3].toString();
				String toutCode = objArr[4].toString();
				Long version = Long.parseLong(objArr[5].toString());
				LocalDateTime createdAt = Timestamp.valueOf(objArr[6].toString()).toLocalDateTime();
				String createdBy = objArr[7].toString();
				LocalDateTime updatedAt = Timestamp.valueOf(objArr[8].toString()).toLocalDateTime();
				String updatedBy = objArr[9].toString();
				Boolean isActive = Boolean.parseBoolean(objArr[10].toString());
				
				
				TransactionsOut transactionsOut = new TransactionsOut();
				transactionsOut.setId(toutId);
				transactionsOut.setTransactionsOutCode(toutCode);

				transactionsIn.setId(tinId);
				transactionsIn.setTransactionsInCode(tinCode);
				transactionsIn.setTransactionsInDate(tinDate);
				transactionsIn.setTransactionsOut(transactionsOut);
				transactionsIn.setVersion(version);
				transactionsIn.setCreatedAt(createdAt);
				transactionsIn.setCreatedBy(createdBy);
				transactionsIn.setUpdatedAt(updatedAt);
				transactionsIn.setUpdatedBy(updatedBy);
				transactionsIn.setIsActive(isActive);

			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
		}

		return transactionsIn;
	}

	@Override
	public TransactionsIn saveOrUpdate(TransactionsIn transactionsIn) throws Exception {
		return save(transactionsIn);
	}

}
