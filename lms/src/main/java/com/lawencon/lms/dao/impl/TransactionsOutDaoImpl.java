package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.TransactionsOutDao;
import com.lawencon.lms.model.TransactionsOut;

public class TransactionsOutDaoImpl extends BaseDaoImpl<TransactionsOut> implements TransactionsOutDao {


	@Override
	public TransactionsOut findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public TransactionsOut findByCode(String code) throws Exception {
		TransactionsOut transactionsOut = new TransactionsOut();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT id,transactions_out_code,check_out_date,expired_date,created_by,created_date,isactive,update_by,update_date,version");
			sql.append(" FROM transactions_out to");
			sql.append(" WHERE transactions_out_code = :code ");
			Object result = createNativeQuery(sql.toString()).setParameter("code", code).getSingleResult();
			if (result != null) {
				transactionsOut = new TransactionsOut();
				Object[] objArr = (Object[]) result;
				transactionsOut.setId(objArr[0].toString());
				transactionsOut.setTransactionsOutCode(objArr[1].toString());
				transactionsOut.setCheckOutDate(((Timestamp) objArr[2]).toLocalDateTime().toLocalDate());
				transactionsOut.setExpiredDate(((Timestamp) objArr[3]).toLocalDateTime().toLocalDate());
				transactionsOut.setCreatedBy(objArr[4].toString());
				transactionsOut.setCreatedAt(((Timestamp) objArr[5]).toLocalDateTime());
				transactionsOut.setIsActive((Boolean) objArr[6]);
				transactionsOut.setVersion(Long.valueOf(objArr[9].toString()));
			}
		}catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Found more than one");
		}
		return transactionsOut;
	}

	@Override
	public List<TransactionsOut> findAll() throws Exception {
		return getAll();
    }


	@Override
	public TransactionsOut saveOrUpdate(TransactionsOut transactionsOut) throws Exception {
		return save(transactionsOut);
	}
}
