package com.lawencon.lms.dao.impl;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.TransactionsDetailOutDao;
import com.lawencon.lms.model.TransactionsDetailOut;

public class TransactionsDetailOutDaoImpl extends BaseDaoImpl<TransactionsDetailOut>
		implements TransactionsDetailOutDao {

	@Override
	public TransactionsDetailOut findById(String id) throws Exception {
		return getById(id);
	}

}
