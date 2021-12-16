package com.lawencon.lms.dao;

import java.util.List;

import com.lawencon.lms.model.Histories;

public interface HistoriesDao {
	Histories saveOrUpdate(Histories histories) throws Exception;
	
	Histories findById(String id) throws Exception;
	
	List<Histories> findAll() throws Exception;
	
	List<Histories> findByUsersId(String usersId) throws Exception;
}
