package com.lawencon.lms.dao;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lawencon.base.ConnHandler;
import com.lawencon.lms.model.StatusesTransactions;

@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@DataJpaTest
class StatusesTransactionsDaoTest {

	@Autowired
	private StatusesTransactionsDao statusesTransactionsDao;
	
	private String idInserted = "";
	
	@Test
	@Order(1)
	public void insert() throws Exception {
		ConnHandler.begin();
		StatusesTransactions statusesTransactions = new StatusesTransactions();
		statusesTransactions.setStatusesTransactionsCode("DEP");
		statusesTransactions.setStatusesTransactionsName("DEPLOYED");
		statusesTransactions.setCreatedBy("test");
		statusesTransactionsDao.saveOrUpdate(statusesTransactions);
		ConnHandler.commit();

		idInserted = statusesTransactions.getId();

		assertNotNull(statusesTransactions.getId());
	}

}