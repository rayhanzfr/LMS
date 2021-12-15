package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.LocationsDao;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Locations;

@Repository()
public class LocationsDaoImpl extends BaseDaoImpl<Locations> implements LocationsDao {

	@Override
	public List<Locations> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Locations findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Locations findByCode(String code) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT l.id, l.locations_code, l.locations_deploy, c.companies_name, l.version, l.created_at, l.created_by, l.updated_at, l.updated_by, l.is_active ");
		sql.append(" FROM locations as l ");
		sql.append(" INNER JOIN companies as c ON c.id = l.locations_id ");
		sql.append(" WHERE l.locations_code = :locations_code ");

		Locations locations = null;

		try {
			Object resultQuery = createNativeQuery(sql.toString()).setParameter("locations_code", code)
					.getSingleResult();

			if (resultQuery != null) {
				Object[] objArr = (Object[]) resultQuery;
				locations = new Locations();

				String id = objArr[0].toString();
				String locationsCode = objArr[1].toString();
				String locationsDeploy = objArr[2].toString();
				String companiesName = objArr[3].toString();
				Integer version = (Integer) objArr[4];
				LocalDateTime createdAt = Timestamp.valueOf(objArr[5].toString()).toLocalDateTime();
				String createdBy = objArr[6].toString();
				LocalDateTime updatedAt = Timestamp.valueOf(objArr[7].toString()).toLocalDateTime();
				String updatedBy = objArr[8].toString();
				Boolean isActive = Boolean.parseBoolean(objArr[9].toString());

				Companies companies = new Companies();
				companies.setCompaniesName(companiesName);

				locations.setId(id);
				locations.setLocationsCode(locationsCode);
				locations.setLocationsDeploy(locationsDeploy);
				locations.setCompanies(companies);
				locations.setVersion(version);
				locations.setCreatedAt(createdAt);
				locations.setCreatedBy(createdBy);
				locations.setUpdatedAt(updatedAt);
				locations.setUpdatedBy(updatedBy);
				locations.setIsActive(isActive);
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
		}

		return locations;
	}

	@Override
	public Locations saveOrUpdate(Locations locations) throws Exception {
		return save(locations);
	}

	@Override
	public String countData() throws Exception {
		String lastRegist = null;
		
		String sql = "SELECT COUNT(l) FROM Locations as l";
	
		Object resultQuery = createQuery(sql, Locations.class).getSingleResult();
		lastRegist = resultQuery.toString();
		return lastRegist;
	}
	
	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
