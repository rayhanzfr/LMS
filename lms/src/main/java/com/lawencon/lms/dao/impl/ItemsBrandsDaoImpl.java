package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.ItemsBrandsDao;
import com.lawencon.lms.model.ItemsBrands;

public class ItemsBrandsDaoImpl extends BaseDaoImpl<ItemsBrands> implements ItemsBrandsDao{
	
	@Override
	public ItemsBrands findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public ItemsBrands findByCode(String code) throws Exception {
		ItemsBrands itemsBrands = new ItemsBrands();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT id,items_brands_code,items_brands_name,created_by,created_date,isactive,update_by,update_date,version");
			sql.append("FROM items_brands WHERE items_brands_code = :code ");
			Object result = createNativeQuery(sql.toString()).setParameter("code", code).getSingleResult();
			if (result != null) {
				itemsBrands = new ItemsBrands();
				Object[] objArr = (Object[]) result;
				itemsBrands.setId(objArr[0].toString());
				itemsBrands.setItemsBrandsCode(objArr[1].toString());
				itemsBrands.setItemsBrandsName(objArr[2].toString());
				itemsBrands.setCreatedBy(objArr[3].toString());
				itemsBrands.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
				itemsBrands.setIsActive((Boolean) objArr[5]);
				itemsBrands.setVersion((Integer)objArr[8]);
			}
		}catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Found more than one");
		}
		return itemsBrands;
	}

	@Override
	public List<ItemsBrands> findAll() throws Exception {
		return getAll();
    }

	@Override
	public ItemsBrands saveOrUpdate(ItemsBrands itemsBrands) throws Exception {
		return save(itemsBrands);
	}
	
	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}
}
