package com.lawencon.lms.service;

import java.util.List;

import com.lawencon.lms.dto.statusesinout.SaveStatusesInOutResDto;
import com.lawencon.lms.dto.statusesinout.UpdateStatusesInOutResDto;
import com.lawencon.lms.model.StatusesInOut;

public interface StatusesInOutService {
	List<StatusesInOut> findAll() throws Exception;

	StatusesInOut findById(String id) throws Exception;

	StatusesInOut findByCode(String code) throws Exception;

	SaveStatusesInOutResDto save(StatusesInOut statusesInOut) throws Exception;

	UpdateStatusesInOutResDto update(StatusesInOut statusesInOut) throws Exception;

	Boolean removeById(String id) throws Exception;
}
