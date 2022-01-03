package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.TransactionsDetailInDao;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.model.StatusesTransactions;
import com.lawencon.lms.model.TransactionsDetailIn;
import com.lawencon.lms.model.TransactionsDetailOut;
import com.lawencon.lms.model.TransactionsIn;

@Repository()
public class TransactionsDetailInDaoImpl extends BaseDaoImpl<TransactionsDetailIn> implements TransactionsDetailInDao {

	@Override
	public List<TransactionsDetailIn> findAll() throws Exception {
		return getAll();
	}

	@Override
	public TransactionsDetailIn findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<TransactionsDetailIn> findByTransactionInCode(String code) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT tdo ");
		sql.append(" FROM TransactionsDetailIn tdo ");
		sql.append(" INNER JOIN FETCH tdo.transactionsIn ");
		sql.append(" LEFT JOIN FETCH tdo.locations l ");
		sql.append(" LEFT JOIN FETCH tdo.employees e ");
		sql.append(" INNER JOIN FETCH tdo.assets a ");
		sql.append(" WHERE tdo.transactionsIn.transactionsInCode = :code");
		sql.append(" AND (l IS NOT NULL OR l IS NULL) ");
		sql.append(" AND (e IS NOT NULL OR e IS NULL) ");
		
		List<TransactionsDetailIn> listDetail = createQuery(sql.toString(), TransactionsDetailIn.class)
				.setParameter("code", code)
				.getResultList();
	return listDetail;
	}

	@Override
	public TransactionsDetailIn saveOrUpdate(TransactionsDetailIn transactionsIn) throws Exception {
		return save(transactionsIn);
	}

}
