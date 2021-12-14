package com.lawencon.lms.dao.impl;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.InvoicesDao;
import com.lawencon.lms.model.Invoices;

public class InvoicesDaoImpl extends BaseDaoImpl<Invoices> implements InvoicesDao{

	@Override
	public List<Invoices> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Invoices findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Invoices findByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoices saveOrUpdate(Invoices invoices) throws Exception {
		return save(invoices);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
