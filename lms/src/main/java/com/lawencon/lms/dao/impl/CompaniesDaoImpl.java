package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.CompaniesDao;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Files;

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
		sql.append(" SELECT c.id, c.companies_code, c.companies_name, c.companies_phone, c.companies_address, f.file, f.extensions, c.version, c.created_at, c.created_by, c.updated_at, c.updated_by, c.is_active ");
		sql.append(" FROM companies as c");
		sql.append(" INNER JOIN files as f ON f.id = c.files_id ");
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
				byte[] file = objArr[5].toString().getBytes();
				String extensions = objArr[6].toString();
				Long version = Long.parseLong(objArr[7].toString());
				LocalDateTime createdAt = Timestamp.valueOf(objArr[8].toString()).toLocalDateTime();
				String createdBy = objArr[9].toString();
				LocalDateTime updatedAt = Timestamp.valueOf(objArr[10].toString()).toLocalDateTime();
				String updatedBy = objArr[11].toString();
				Boolean isActive = Boolean.parseBoolean(objArr[12].toString());
				
				Files files = new Files();
				files.setFile(file);
				files.setExtensions(extensions);
				
				companies.setId(id);
				companies.setCompaniesCode(companiesCode);
				companies.setCompaniesName(companiesName);
				companies.setCompaniesPhone(companiesPhone);
				companies.setCompaniesAddress(companiesAddress);
				companies.setFiles(files);
				companies.setVersion(version);
				companies.setCreatedAt(createdAt);
				companies.setCreatedBy(createdBy);
				companies.setUpdatedAt(updatedAt);
				companies.setUpdatedBy(updatedBy);
				companies.setIsActive(isActive);
				
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NoResultException("Found more than one");
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
