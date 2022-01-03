package com.lawencon.lms.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lawencon.base.ConnHandler;
import com.lawencon.lms.model.Permissions;

@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@DataJpaTest
class PermissionssDaoTest {

	@Autowired
	private PermissionsDao permissionsDao;
	
	private String idInserted = "";
	
	@Test
	@Order(1)
	public void insert() throws Exception {
		ConnHandler.begin();
		Permissions permissions = new Permissions();
		permissions.setPermissionsCode("DEP");
		permissions.setPermissionsName("DEPLOYED");
		permissions.setCreatedBy("test");
		permissionsDao.saveOrUpdate(permissions);
		ConnHandler.commit();

		idInserted = permissions.getId();

		assertNotNull(permissions.getId());
	}

}
