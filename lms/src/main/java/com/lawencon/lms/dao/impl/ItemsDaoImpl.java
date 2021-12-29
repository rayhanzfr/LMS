package com.lawencon.lms.dao.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.ItemsDao;
import com.lawencon.lms.model.Files;
import com.lawencon.lms.model.Items;
import com.lawencon.lms.model.ItemsBrands;
import com.lawencon.lms.model.ItemsTypes;

@Repository
public class ItemsDaoImpl extends BaseDaoImpl<Items> implements ItemsDao {

	@Override
	public Items findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Items findByCode(String code) throws Exception {
		try {
			Items items = new Items();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT i ");
			sql.append(" FROM Items i");
			sql.append(" INNER JOIN FETCH i.files");
			sql.append(" INNER JOIN FETCH i.itemsTypes");
			sql.append(" INNER JOIN FETCH i.itemsBrands");
			sql.append(" WHERE i.itemsCode = :code ");
			items = createQuery(sql.toString(),Items.class).setParameter("code", code).getSingleResult();
			return items;
		}catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Found more than one");
		}
	}

	@Override
	public List<Items> findAll() throws Exception {
		return getAll();
    }


	@Override
	public Items saveOrUpdate(Items items) throws Exception {
		return save(items);
	}
	
	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public Integer countData() throws Exception {
		String sql = "SELECT COUNT(i.id) FROM Items i ";
		Object result = createNativeQuery(sql).getSingleResult();
		BigInteger results = new BigInteger(result.toString());
		Integer resultsInteger = results.intValue();
		return resultsInteger;
	}
}
