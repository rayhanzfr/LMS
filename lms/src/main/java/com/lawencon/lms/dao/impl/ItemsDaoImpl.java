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
		Items items = new Items();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT items.id,files_id,items_types_id,items_brands_id,items_code,items_name,items.created_by,items.created_at,items.is_active,items.updated_by,items.updated_at,items.version");
			sql.append(" FROM items");
			sql.append(" INNER JOIN files f ON f.id = items.files_id");
			sql.append(" INNER JOIN items_types it ON it.id = items.items_types_id");
			sql.append(" INNER JOIN items_brands ib ON ib.id = items.items_brands_id");
			sql.append(" WHERE items_code = :code ");
			Object result = createNativeQuery(sql.toString()).setParameter("code", code).getSingleResult();
			if (result != null) {
				items = new Items();
				Object[] objArr = (Object[]) result;
				items.setId(objArr[0].toString());
				Files files = new Files();
				files.setId(objArr[1].toString());
				items.setFiles(files);
				ItemsTypes itemsTypes = new ItemsTypes();
				itemsTypes.setId(objArr[2].toString());
				items.setItemsTypes(itemsTypes);
				ItemsBrands itemsBrands = new ItemsBrands();
				itemsBrands.setId(objArr[3].toString());
				items.setItemsBrands(itemsBrands);
				items.setItemsCode(objArr[4].toString());
				items.setItemsName(objArr[5].toString());
				items.setCreatedBy(objArr[6].toString());
				items.setCreatedAt(((Timestamp) objArr[7]).toLocalDateTime());
				items.setIsActive((Boolean) objArr[8]);
				items.setVersion((Integer)objArr[11]);
			}
		}catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Found more than one");
		}
		return items;
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
