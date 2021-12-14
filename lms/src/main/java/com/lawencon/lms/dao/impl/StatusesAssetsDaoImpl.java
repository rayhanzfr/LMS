package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
		sql.append(" SELECT id, statuses_assets_code, statuses_assets_name, version, created_at, created_by, updated_at, updated_by, is_active  ");
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
				Integer version = (Integer) objArr[3];
				LocalDateTime createdAt = Timestamp.valueOf(objArr[4].toString()).toLocalDateTime();
				String createdBy = objArr[5].toString();
				LocalDateTime updatedAt = Timestamp.valueOf(objArr[6].toString()).toLocalDateTime();
				String updatedBy = objArr[7].toString();
				Boolean isActive = Boolean.parseBoolean(objArr[8].toString());
				
				statusesAssets.setId(id);
				statusesAssets.setStatusesAssetsCode(statusesAssetsCode);
				statusesAssets.setStatusesAssetsName(statusesAssetsName);
				statusesAssets.setVersion(version);
				statusesAssets.setCreatedAt(createdAt);
				statusesAssets.setCreatedBy(createdBy);
				statusesAssets.setUpdatedAt(updatedAt);
				statusesAssets.setUpdatedBy(updatedBy);
				statusesAssets.setIsActive(isActive);
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
