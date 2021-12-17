package com.lawencon.lms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.AssetsDao;
import com.lawencon.lms.model.Assets;

@Repository
public class AssetsDaoImpl extends BaseDaoImpl<Assets> implements AssetsDao {

	@Override
	public List<Assets> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Assets findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Assets findByAssetsName(String assetsName) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH Items AS i ");
		sql.append(" INNER JOIN FETCH ItemsTypes AS it ");
		sql.append(" INNER JOIN FETCH ItemsBrands AS ib ");
		sql.append(" INNER JOIN FETCH Invoices AS invo ");
		sql.append(" INNER JOIN FETCH StatusesAssets AS sa ");
		sql.append(" INNER JOIN FETCH StatusesInOut AS sio ");
		sql.append(" WHERE a.assetsName = :assetsName ");

		Assets assets = createQuery(sql.toString(), Assets.class).setParameter("assetsName", assetsName)
				.getSingleResult();
		return assets;
	}

	@Override
	public List<Assets> findByItemsCode(String itemsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM assets AS a ");
		sql.append(" INNER JOIN FETCH Items AS i ");
		sql.append(" INNER JOIN FETCH ItemsTypes AS it ");
		sql.append(" INNER JOIN FETCH ItemsBrands AS ib ");
		sql.append(" INNER JOIN FETCH Invoices AS invo ");
		sql.append(" INNER JOIN FETCH StatusesAssets AS sa ");
		sql.append(" INNER JOIN FETCH StatusesInOut AS sio ");
		sql.append(" WHERE i.itemsCode = :itemsCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("assetsName", itemsCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByBrandsCode(String brandsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM assets AS a ");
		sql.append(" INNER JOIN FETCH Items AS i ");
		sql.append(" INNER JOIN FETCH ItemsTypes AS it ");
		sql.append(" INNER JOIN FETCH ItemsBrands AS ib ");
		sql.append(" INNER JOIN FETCH Invoices AS invo ");
		sql.append(" INNER JOIN FETCH StatusesAssets AS sa ");
		sql.append(" INNER JOIN FETCH StatusesInOut AS sio ");
		sql.append(" WHERE ib.itemsBrandsCode= :brandsCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("brandsCode", brandsCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByItemsTypesCode(String itemsTypesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM assets AS a ");
		sql.append(" INNER JOIN FETCH Items AS i ");
		sql.append(" INNER JOIN FETCH ItemsTypes AS it ");
		sql.append(" INNER JOIN FETCH ItemsBrands AS ib ");
		sql.append(" INNER JOIN FETCH Invoices AS invo ");
		sql.append(" INNER JOIN FETCH StatusesAssets AS sa ");
		sql.append(" INNER JOIN FETCH StatusesInOut AS sio ");
		sql.append(" WHERE it.itemsTypesCode = :itemsTypesCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("itemsTypesCode", itemsTypesCode).getResultList();
		return listAssets;

	}

	@Override
	public List<Assets> findByStatusesAssetsCode(String statusesAssetsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM assets AS a ");
		sql.append(" INNER JOIN FETCH Items AS i ");
		sql.append(" INNER JOIN FETCH ItemsTypes AS it ");
		sql.append(" INNER JOIN FETCH ItemsBrands AS ib ");
		sql.append(" INNER JOIN FETCH Invoices AS invo ");
		sql.append(" INNER JOIN FETCH StatusesAssets AS sa ");
		sql.append(" INNER JOIN FETCH StatusesInOut AS sio ");
		sql.append(" WHERE sa.statusesAssetsCode = :statusesAssetsCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("statusesAssetsCode", statusesAssetsCode).getResultList();
		return listAssets;

	}

	@Override
	public List<Assets> findByStatusesInOutCode(String statusesInOutCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM assets AS a ");
		sql.append(" INNER JOIN FETCH Items AS i ");
		sql.append(" INNER JOIN FETCH ItemsTypes AS it ");
		sql.append(" INNER JOIN FETCH ItemsBrands AS ib ");
		sql.append(" INNER JOIN FETCH Invoices AS invo ");
		sql.append(" INNER JOIN FETCH StatusesAssets AS sa ");
		sql.append(" INNER JOIN FETCH StatusesInOut AS sio ");
		sql.append(" WHERE sio.statusesInOutCode= :statusesInOutCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("statusesInOutCode", statusesInOutCode).getResultList();
		return listAssets;

	}

	@Override
	public Assets saveOrUpdate(Assets assets) throws Exception {
		return save(assets);
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return deleteById(id);
	}

	@Override
	public List<Assets> findByReq(String itemsCode, String itemsTypesCode, String itemsBrandsCode,
			String statusesAssetsCode, String statusesInOutCode, Integer total) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM assets AS a ");
		sql.append(" INNER JOIN FETCH Items AS i ");
		sql.append(" INNER JOIN FETCH ItemsTypes AS it ");
		sql.append(" INNER JOIN FETCH ItemsBrands AS ib ");
		sql.append(" INNER JOIN FETCH Invoices AS invo ");
		sql.append(" INNER JOIN FETCH StatusesAssets AS sa ");
		sql.append(" INNER JOIN FETCH StatusesInOut AS sio ");
		sql.append(" WHERE i.itemsCode= :itemsCode ");
		sql.append(" WHERE it.itemsTypesCode= :itemsTypesCode ");
		sql.append(" WHERE ib.itemsBrandsCode= :itemsBrandsCode ");
		sql.append(" WHERE sa.statusesAssetsCode= :statusesAssetsCode ");
		sql.append(" WHERE sio.statusesInOutCode= :statusesInOutCode ");
		sql.append(" LIMIT :total ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("itemsCode", itemsCode)
				.setParameter("itemsTypesCode", itemsTypesCode).setParameter("itemsBrandsCode", itemsBrandsCode)
				.setParameter("statusesAssetsCode", statusesAssetsCode)
				.setParameter("statusesInOutCode", statusesInOutCode).setParameter("total", total).getResultList();
		return listAssets;
	}

	@Override
	public String countData() throws Exception {
		Object resultQuery = createQuery("SELECT COUNT(a.id) FROM Assets a", Assets.class).getSingleResult();
		String count = resultQuery.toString();
		return count;
	}

}
