package com.lawencon.lms.service;

import java.util.List;
import java.util.Map;

import com.lawencon.lms.dto.histories.HistoriesReportResDto;
import com.lawencon.lms.model.Histories;

public interface HistoriesService {
	List<Histories> findAll() throws Exception;
	
	Histories findById(String id) throws Exception;
	
	Histories save(Histories files) throws Exception;
	
	List<Histories> findByUsersId(String usersId) throws Exception;
	
	Map<String,Object> findHistoriesReport() throws Exception;
	
	Map<String,Object> findHistoriesReportNonAdmin(String companiesCode) throws Exception;
}
