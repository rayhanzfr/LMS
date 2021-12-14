package com.lawencon.lms.dao;

import com.lawencon.lms.model.TransactionsDetailOut;

public interface TransactionsDetailOutDao {
	TransactionsDetailOut findById(String id) throws Exception;
}
