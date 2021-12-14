package com.lawencon.lms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.model.StatusesAssets;

public class StatusesAssetsDaoImpl extends BaseDaoImpl<StatusesAssets> implements StatusesAssetsDao{

	@Override
	public List<StatusesAssets> findAll() throws Exception {
		return getAll();
	}

	@Override
	public StatusesAssets findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public StatusesAssets findByCode(String code) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT id, statuses_assets_code, statuses_assets_name ");
		sql.append(" FROM statuses_assets ");
		sql.append(" WHERE statuses_assets_code = :statuses_assets_code ");
		
		StatusesAssets statusesAssets = null;
		
		try {
			Object resultQuery = createNativeQuery(sql.toString()).setParameter("statuses_assets_code", code)
					.getSingleResult();

			if (resultQuery != null) {
				Object[] objArr = (Object[]) resultQuery;
				statusesAssets = new StatusesAssets();
				
				String id = objArr[0].toString();
				String statusesAssetsCode = objArr[1].toString();
				String statusesAssetsName = objArr[2].toString();
				
				statusesAssets.setId(id);
				statusesAssets.setStatusesAssetsCode(statusesAssetsCode);;
				statusesAssets.setStatusesAssetsName(statusesAssetsName);;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
		}
		
		return statusesAssets;
	}

	@Override
	public StatusesAssets saveOrUpdate(StatusesAssets companies) throws Exception {
		return save(companies);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
