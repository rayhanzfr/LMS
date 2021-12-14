package com.lawencon.lms.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.InvoicesDao;
import com.lawencon.lms.model.Invoices;

public class InvoicesDaoImpl extends BaseDaoImpl<Invoices> implements InvoicesDao {

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
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT id, invoices_code, invoices_date, store_name, price ");
		sql.append(" FROM invoices ");
		sql.append(" WHERE invoices_code = :invoices_code ");

		Invoices invoices = null;

		try {
			Object resultQuery = createNativeQuery(sql.toString()).setParameter("invoices_code", code)
					.getSingleResult();

			if (resultQuery != null) {
				Object[] objArr = (Object[]) resultQuery;
				invoices = new Invoices();

				String id = objArr[0].toString();
				String invoicesCode = objArr[1].toString();
				LocalDateTime invoicesDate = Timestamp.valueOf(objArr[2].toString()).toLocalDateTime();
				String storeName = objArr[3].toString();
				BigDecimal price = new BigDecimal(objArr[4].toString());

				invoices.setId(id);
				invoices.setInvoicesCode(invoicesCode);
				invoices.setInvoicesDate(invoicesDate);
				invoices.setStoreName(storeName);
				invoices.setPrice(price);;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
		}

		return invoices;
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
