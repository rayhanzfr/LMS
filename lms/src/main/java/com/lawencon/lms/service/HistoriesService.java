package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.histories.HistoriesReportResDto;
import com.lawencon.lms.model.Histories;

public interface HistoriesService {
	List<Histories> findAll() throws Exception;
	
	Histories findById(String id) throws Exception;
	
	Histories save(Histories files) throws Exception;
	
	List<Histories> findByUsersId(String usersId) throws Exception;
	
	List<HistoriesReportResDto> findHistoriesReport() throws Exception;
}
