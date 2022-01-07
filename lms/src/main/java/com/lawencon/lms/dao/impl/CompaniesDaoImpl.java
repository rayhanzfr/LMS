package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.CompaniesDao;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Files;

@Repository()
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
		sql.append(" SELECT c ");
		sql.append(" FROM Companies c ");
		sql.append(" LEFT JOIN FETCH c.files f ");
		sql.append(" WHERE c.companiesCode= :code ");
		
		Companies companies = createQuery(sql.toString(),Companies.class)
				.setParameter("code", code)
				.getSingleResult();
		return companies;
//		sql.append(
//				" SELECT c.id, c.companies_code, c.companies_name, c.companies_phone, c.companies_address, c.files_id, c.version, c.created_at, c.created_by, c.updated_at, c.updated_by, c.is_active ");
//		sql.append(" FROM companies as c");
//		sql.append(" INNER JOIN files as f ON f.id = c.files_id ");
//		sql.append(" WHERE companies_code = :companies_code ");
//
//		Companies companies = null;
//
//		try {
//			Object resultQuery = createNativeQuery(sql.toString()).setParameter("companies_code", code)
//					.getSingleResult();
//
//			if (resultQuery != null) {
//				Object[] objArr = (Object[]) resultQuery;
//				companies = new Companies();
//
//				String id = objArr[0].toString();
//				String companiesCode = objArr[1].toString();
//				String companiesName = objArr[2].toString();
//				String companiesPhone = objArr[3].toString();
//				String companiesAddress = objArr[4].toString();
//				String filesId = objArr[5].toString();
//				Integer version = (Integer) objArr[6];
//				LocalDateTime createdAt = Timestamp.valueOf(objArr[7].toString()).toLocalDateTime();
//				String createdBy = objArr[8].toString();
//
//				if (objArr[9] != null) {
//					LocalDateTime updatedAt = Timestamp.valueOf(objArr[9].toString()).toLocalDateTime();
//					String updatedBy = objArr[10].toString();
//					companies.setUpdatedAt(updatedAt);
//					companies.setUpdatedBy(updatedBy);
//				}
//
//				Boolean isActive = Boolean.parseBoolean(objArr[11].toString());
//
//				Files files = new Files();
//				files.setId(filesId);
//				;
//
//				companies.setId(id);
//				companies.setCompaniesCode(companiesCode);
//				companies.setCompaniesName(companiesName);
//				companies.setCompaniesPhone(companiesPhone);
//				companies.setCompaniesAddress(companiesAddress);
//				companies.setFiles(files);
//				companies.setVersion(version);
//				companies.setCreatedAt(createdAt);
//				companies.setCreatedBy(createdBy);
//				companies.setIsActive(isActive);
//
//			}
//		} catch (NoResultException e) {
//			e.printStackTrace();
//			throw new NoResultException("Not Found");
//		} catch (NonUniqueResultException e) {
//			e.printStackTrace();
//			throw new NonUniqueResultException("Found more than one");
//		}
//
//		return companies;
	}

	@Override
	public Companies saveOrUpdate(Companies companies) throws Exception {
		return save(companies);
	}

	@Override
	public Integer countData() throws Exception {
		String sql = "SELECT COUNT(c) FROM Companies as c";
		Object result = createNativeQuery(sql).getSingleResult();
		Integer results = Integer.valueOf(result.toString());
		return results;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
