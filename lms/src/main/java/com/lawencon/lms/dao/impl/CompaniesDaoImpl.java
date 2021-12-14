package com.lawencon.lms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.CompaniesDao;
import com.lawencon.lms.model.Companies;

public class CompaniesDaoImpl extends BaseDaoImpl<Companies> implements CompaniesDao {

	@Override
	public List<Companies> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Companies findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Companies findByCode(String code) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT id, companies_code, companies_name, companies_phone, companies_address ");
		sql.append(" FROM companies ");
		sql.append(" WHERE companies_code = :companies_code ");

		Companies companies = null;

		try {
			Object resultQuery = createNativeQuery(sql.toString()).setParameter("companies_code", code)
					.getSingleResult();

			if (resultQuery != null) {
				Object[] objArr = (Object[]) resultQuery;
				companies = new Companies();
				
				String id = objArr[0].toString();
				String companiesCode = objArr[1].toString();
				String companiesName = objArr[2].toString();
				String companiesPhone = objArr[3].toString();
				String companiesAddress = objArr[4].toString();
				
				companies.setId(id);
				companies.setCompaniesCode(companiesCode);
				companies.setCompaniesName(companiesName);
				companies.setCompaniesPhone(companiesPhone);
				companies.setCompaniesAddress(companiesAddress);
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
		}

		return companies;
	}

	@Override
	public Companies saveOrUpdate(Companies companies) throws Exception {
		return save(companies);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
