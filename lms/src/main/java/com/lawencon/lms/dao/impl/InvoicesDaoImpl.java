package com.lawencon.lms.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.InvoicesDao;
import com.lawencon.lms.model.Invoices;

@Repository()
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
		sql.append(
				" SELECT id, invoices_code, invoices_date, store_name, price, version, created_at, created_by, updated_at, updated_by, is_active ");
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
				Integer version = (Integer) objArr[5];
				LocalDateTime createdAt = Timestamp.valueOf(objArr[6].toString()).toLocalDateTime();
				String createdBy = objArr[7].toString();
				if (objArr[8] != null) {
					LocalDateTime updatedAt = Timestamp.valueOf(objArr[8].toString()).toLocalDateTime();
					invoices.setUpdatedAt(updatedAt);
				}
				if (objArr[9] != null) {
					String updatedBy = objArr[9].toString();
					invoices.setUpdatedBy(updatedBy);
				}
				Boolean isActive = Boolean.parseBoolean(objArr[10].toString());

				invoices.setId(id);
				invoices.setInvoicesCode(invoicesCode);
				invoices.setInvoicesDate(invoicesDate);
				invoices.setStoreName(storeName);
				invoices.setPrice(price);
				invoices.setVersion(version);
				invoices.setCreatedAt(createdAt);
				invoices.setCreatedBy(createdBy);

				invoices.setIsActive(isActive);
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NonUniqueResultException("Found more than one");
		}

		return invoices;
	}

	@Override
	public Invoices saveOrUpdate(Invoices invoices) throws Exception {
		return save(invoices);
	}

	@Override
	public Integer countData() throws Exception {
		String sql = "SELECT COUNT(i) FROM Invoices as i";
		Object result = createNativeQuery(sql).getSingleResult();
		Integer results = Integer.valueOf(result.toString());
		return results;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public List<Invoices> findByCompanies(String companiesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT i.id , i.invoices_code , i.store_name ,i.price , i.invoices_date ");
		sql.append(" FROM invoices i ");
		sql.append(" WHERE i.created_by = (SELECT r.id ");
		sql.append(" FROM users u ");
		sql.append(" INNER JOIN roles r ON u.roles_id = r.id ");
		sql.append(" WHERE r.roles_code = 'ROLES1' AND c.companies_code=:code) ");
		sql.append(" OR i.created_by = (SELECT r.id ");
		sql.append(" FROM users u ");
		sql.append(" INNER JOIN roles r ON u.roles_id = r.id ");
		sql.append(" WHERE r.roles_code = 'ROLES2' AND c.companies_code=:code) ");
		sql.append(" OR i.created_by = (SELECT r.id ");
		sql.append(" FROM users u ");
		sql.append(" INNER JOIN roles r ON u.roles_id = r.id ");
		sql.append(" WHERE r.roles_code = 'ROLES3' AND c.companies_code=:code) ");
		
		
		List<?>result = createNativeQuery(sql.toString())
				.setParameter("code", companiesCode)
				.getResultList();
		List<Invoices> listInvoices = new ArrayList<Invoices>();
		result.forEach(rs->{
			Object[] obj = (Object[]) rs;
			Invoices invoices = new Invoices();
			invoices.setId(obj[0].toString());
			invoices.setInvoicesCode(obj[1].toString());
			invoices.setStoreName(obj[2].toString());
			BigDecimal price  = new BigDecimal(obj[3].toString());
			invoices.setPrice(price);
			invoices.setInvoicesDate(((Timestamp) obj[4]).toLocalDateTime());
			listInvoices.add(invoices);
		});
		return listInvoices;
	}

}
