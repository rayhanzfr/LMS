package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.model.StatusesAssets;

@Repository()
public class StatusesAssetsDaoImpl extends BaseDaoImpl<StatusesAssets> implements StatusesAssetsDao {

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
		sql.append(
				" SELECT id, statuses_assets_code, statuses_assets_name, version, created_at, created_by, updated_at, updated_by, is_active  ");
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
				if(objArr[6]!=null) {
					LocalDateTime updatedAt = Timestamp.valueOf(objArr[6].toString()).toLocalDateTime();
					statusesAssets.setUpdatedAt(updatedAt);
				}
				if(objArr[7]!=null) {
					String updatedBy = objArr[7].toString();
					statusesAssets.setUpdatedBy(updatedBy);
				}
				Boolean isActive = Boolean.parseBoolean(objArr[8].toString());

				statusesAssets.setId(id);
				statusesAssets.setStatusesAssetsCode(statusesAssetsCode);
				statusesAssets.setStatusesAssetsName(statusesAssetsName);
				statusesAssets.setVersion(version);
				statusesAssets.setCreatedAt(createdAt);
				statusesAssets.setCreatedBy(createdBy);
				statusesAssets.setIsActive(isActive);
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NonUniqueResultException("Found more than one");
		}

		return statusesAssets;
	}

	@Override
	public StatusesAssets saveOrUpdate(StatusesAssets companies) throws Exception {
		return save(companies);
	}

	@Override
	public String countData() throws Exception {
		String lastRegist = null;

		String sql = "SELECT COUNT(s) FROM StatusesAssets as s";

		Object resultQuery = createQuery(sql, StatusesAssets.class).getSingleResult();
		lastRegist = resultQuery.toString();
		return lastRegist;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

}
