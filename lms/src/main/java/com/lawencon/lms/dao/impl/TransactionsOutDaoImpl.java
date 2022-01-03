package com.lawencon.lms.dao.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.TransactionsOutDao;
import com.lawencon.lms.model.TransactionsOut;

@Repository
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
					" SELECT id,transactions_out_code,created_by,created_at,is_active,updated_by,updated_at,version ");
			sql.append(" FROM transactions_out ");
			sql.append(" WHERE transactions_out_code = :code ");
			Object result = createNativeQuery(sql.toString()).setParameter("code", code).getSingleResult();
			if (result != null) {
				transactionsOut = new TransactionsOut();
				Object[] objArr = (Object[]) result;
				transactionsOut.setId(objArr[0].toString());
				transactionsOut.setTransactionsOutCode(objArr[1].toString());
				transactionsOut.setCreatedBy(objArr[2].toString());
				transactionsOut.setCreatedAt(((Timestamp) objArr[3]).toLocalDateTime());
				transactionsOut.setIsActive((Boolean) objArr[4]);
				transactionsOut.setVersion((Integer) objArr[7]);
			}
		} catch (NoResultException e) {
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

	@Override
	public Integer countData() throws Exception {
		String sql = " SELECT COUNT(id) FROM transactions_out ";
		Object result = createNativeQuery(sql).getSingleResult();
		BigInteger results = new BigInteger(result.toString());
		Integer resultsInteger = results.intValue();
		return resultsInteger;
	}
}
