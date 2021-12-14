package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.ItemsTypesDao;
import com.lawencon.lms.model.ItemsTypes;

@Repository
public class ItemsTypesDaoImpl extends BaseDaoImpl<ItemsTypes> implements ItemsTypesDao {

	@Override
	public List<ItemsTypes> findAll() throws Exception {
		return getAll();
	}

	@Override
	public ItemsTypes findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public ItemsTypes findByCode(String code) throws Exception {
		ItemsTypes itemsTypes = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					" SELECT it.id, it.items_types_code, it.items_types_name, it.created_by, it.created_at, it.updated_by, it.updated_at, it.version");
			sql.append(" FROM items_types as it ");
			sql.append(" WHERE it.items_types_code = :code ");

			Object resultQuery = createNativeQuery(sql.toString()).setParameter("code", code).getSingleResult();
			if (resultQuery != null) {
				Object[] obj = (Object[]) resultQuery;
				itemsTypes = new ItemsTypes();
				itemsTypes.setId(obj[0].toString());
				itemsTypes.setItemsTypesCode(obj[1].toString());
				itemsTypes.setItemsTypesName(obj[2].toString());
				itemsTypes.setCreatedBy(obj[3].toString());
				itemsTypes.setCreatedAt(Timestamp.valueOf(obj[4].toString()).toLocalDateTime());

				if (obj[5] != null) {
					itemsTypes.setUpdatedBy(obj[5].toString());
				}
				if (obj[6] != null) {
					itemsTypes.setUpdatedAt(Timestamp.valueOf(obj[6].toString()).toLocalDateTime());
				}

				itemsTypes.setVersion(Long.valueOf(obj[7].toString()));
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return itemsTypes;
	}

	@Override
	public ItemsTypes saveOrUpdate(ItemsTypes itemsTypes) throws Exception {
		return save(itemsTypes);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return removeById(id);
	}

}
