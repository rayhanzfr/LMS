package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.StatusesTransactionsDao;
import com.lawencon.lms.dto.statusestransactions.SaveStatusesTransactionsResDto;
import com.lawencon.lms.dto.statusestransactions.UpdateStatusesTransactionsResDto;
import com.lawencon.lms.model.StatusesTransactions;
import com.lawencon.lms.service.StatusesTransactionsService;

@Service
public class StatusesTransactionsServiceImpl extends BaseServiceLmsImpl implements StatusesTransactionsService {

	@Autowired
	private StatusesTransactionsDao statusesTransactionsDao;

	@Override
	public SaveStatusesTransactionsResDto save(StatusesTransactions statusesTransactions) throws Exception {
		SaveStatusesTransactionsResDto saveStatusesTransactionsResDto = new SaveStatusesTransactionsResDto();
		try {
			statusesTransactions.setCreatedBy(getIdAuth());
			begin();
			statusesTransactions = statusesTransactionsDao.saveOrUpdate(statusesTransactions);
			commit();
			saveStatusesTransactionsResDto.setId(statusesTransactions.getId());
			saveStatusesTransactionsResDto.setMsg("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return saveStatusesTransactionsResDto;
	}

	@Override
	public UpdateStatusesTransactionsResDto update(StatusesTransactions statusesTransactions) throws Exception {
		UpdateStatusesTransactionsResDto updateStatusesTransactionsResDto = new UpdateStatusesTransactionsResDto();
		try {
			StatusesTransactions statusesTransactionsDb = findByCode(statusesTransactions.getStatusesTransactionsCode());	
			statusesTransactions.setCreatedAt(statusesTransactionsDb.getCreatedAt());
			statusesTransactions.setCreatedBy(statusesTransactionsDb.getCreatedBy());
			statusesTransactions.setUpdatedBy(getIdAuth());

			begin();
			statusesTransactions = statusesTransactionsDao.saveOrUpdate(statusesTransactions);
			commit();
			updateStatusesTransactionsResDto.setVersion(statusesTransactions.getVersion());
			updateStatusesTransactionsResDto.setMsg("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateStatusesTransactionsResDto;
	}

	@Override
	public StatusesTransactions findById(String id) throws Exception {
		return statusesTransactionsDao.findById(id);
	}

	@Override
	public List<StatusesTransactions> findAll() throws Exception {
		return statusesTransactionsDao.findAll();
	}

	@Override
	public StatusesTransactions findByCode(String code) throws Exception {
		return statusesTransactionsDao.findByCode(code);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = statusesTransactionsDao.removeById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
