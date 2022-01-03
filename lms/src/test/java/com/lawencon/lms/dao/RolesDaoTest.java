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
import com.lawencon.lms.model.Roles;

@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@DataJpaTest
class RolesDaoTest {

	@Autowired
	private RolesDao rolesDao;
	
	private String idInserted = "";
	
	@Test
	@Order(1)
	public void insert() throws Exception {
		ConnHandler.begin();
		Roles roles = new Roles();
		roles.setRolesCode("EMP");
		roles.setRolesName("Employee");
		roles.setCreatedBy("test");
		rolesDao.saveOrUpdate(roles);
		ConnHandler.commit();

		idInserted = roles.getId();

		assertNotNull(roles.getId());
	}

}