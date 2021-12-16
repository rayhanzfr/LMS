package com.lawencon.lms.dao.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.StatusesTransactionsDao;
import com.lawencon.lms.model.ItemsBrands;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.StatusesTransactions;

@Repository
public class StatusesTransactionsDaoImpl extends BaseDaoImpl<StatusesTransactions> implements StatusesTransactionsDao {

	@Override
	public StatusesTransactions findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public StatusesTransactions findByCode(String code) throws Exception {
		StatusesTransactions statusesTransactions = new StatusesTransactions();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT st.id,statuses_assets_id,statuses_transactions_code,statuses_transactions_name,st.created_by,st.created_at,st.isactive,st.update_by,st.update_date,st.version");
			sql.append(" FROM statuses_transactions st");
			sql.append(" INNER JOIN statuses_assets sa ON sa.id = st.statuses_assets_id");
			sql.append(" WHERE statuses_transactions_code = :code ");
			Object result = createNativeQuery(sql.toString()).setParameter("code", code).getSingleResult();
			if (result != null) {
				statusesTransactions = new StatusesTransactions();
				Object[] objArr = (Object[]) result;
				statusesTransactions.setId(objArr[0].toString());
				StatusesAssets statusesAssets = new StatusesAssets();
				statusesAssets.setId(objArr[1].toString());
				statusesTransactions.setStatusesAssets(statusesAssets);
				statusesTransactions.setStatusesTransactionsCode(objArr[2].toString());
				statusesTransactions.setStatusesTransactionsName(objArr[3].toString());
				statusesTransactions.setCreatedBy(objArr[4].toString());
				statusesTransactions.setCreatedAt(((Timestamp) objArr[5]).toLocalDateTime());
				statusesTransactions.setIsActive((Boolean) objArr[6]);
				statusesTransactions.setVersion((Integer)objArr[9]);
			}
		}catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Found more than one");
		}
		return statusesTransactions;
	}

	@Override
	public List<StatusesTransactions> findAll() throws Exception {
		return getAll();
    }


	@Override
	public StatusesTransactions saveOrUpdate(StatusesTransactions statusesTransactions) throws Exception {
		return save(statusesTransactions);
	}
	
	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
	
}
