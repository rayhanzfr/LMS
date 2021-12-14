package com.lawencon.lms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.LocationsDao;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Locations;

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
		sql.append(" SELECT l.id, l.locations_code, l.locations_deploy, c.companies_name ");
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

				Companies companies = new Companies();
				companies.setCompaniesName(companiesName);
				
				locations.setId(id);
				locations.setLocationsCode(locationsCode);
				locations.setLocationsDeploy(locationsDeploy);
				locations.setCompanies(companies);
				
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
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
