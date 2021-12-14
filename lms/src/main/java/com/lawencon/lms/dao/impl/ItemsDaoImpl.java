package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.ItemsDao;
import com.lawencon.lms.model.Items;

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
					"SELECT items.id,files_id,items_types_id,items_code,items_name,items.created_by,items.created_date,items.isactive,items.update_by,items.update_date,version");
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
				items.setItemsCode(objArr[1].toString());
				items.setItemsName(objArr[2].toString());
				items.setCreatedBy(objArr[3].toString());
				items.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
				items.setIsActive((Boolean) objArr[5]);
				items.setVersion(Long.valueOf(objArr[8].toString()));
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
}
