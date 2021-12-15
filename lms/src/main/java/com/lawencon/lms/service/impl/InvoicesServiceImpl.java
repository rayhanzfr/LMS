package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.InvoicesDao;
import com.lawencon.lms.dto.invoices.SaveInvoicesResDto;
import com.lawencon.lms.dto.invoices.UpdateInvoicesResDto;
import com.lawencon.lms.model.Invoices;
import com.lawencon.lms.service.InvoicesService;

public class InvoicesServiceImpl extends BaseServiceImpl implements InvoicesService {

	@Autowired
	private InvoicesDao invoicesDao;

	@Override
	public List<Invoices> findAll() throws Exception {
		return invoicesDao.findAll();
	}

	@Override
	public Invoices findById(String id) throws Exception {
		return invoicesDao.findById(id);
	}

	@Override
	public Invoices findByCode(String code) throws Exception {
		return invoicesDao.findByCode(code);
	}

	@Override
	public SaveInvoicesResDto save(Invoices invoices) throws Exception {
		SaveInvoicesResDto saveRes = new SaveInvoicesResDto();
		
		try {
			begin();
			invoices = invoicesDao.saveOrUpdate(invoices);
			commit();
			
			saveRes.setId(invoices.getId());
			saveRes.setMessage("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return saveRes;
	}

	@Override
	public UpdateInvoicesResDto update(Invoices invoices) throws Exception {
		UpdateInvoicesResDto updateRes = new UpdateInvoicesResDto();
		
		try {
			Invoices invoicesDb = findById(invoices.getId());	
			invoices.setCreatedAt(invoicesDb.getCreatedAt());
			invoices.setCreatedBy(invoicesDb.getCreatedBy());

			begin();
			invoices = invoicesDao.saveOrUpdate(invoices);
			commit();
			
			updateRes.setVersion(invoices.getVersion());
			updateRes.setMessage("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateRes;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return invoicesDao.removeById(id);
	}

}
