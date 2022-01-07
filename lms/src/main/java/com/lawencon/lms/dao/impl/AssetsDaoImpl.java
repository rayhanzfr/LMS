package com.lawencon.lms.dao.impl;

import java.math.BigInteger;
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
	public Assets findByAssetsNameCompany(String companiesCode,String assetsName) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE a.assetsName = :assetsName ");
		sql.append(" AND c.companiesCode = :companiesCode ");
		sql.append(" ORDER BY a.createdAt DESC ");

		Assets assets = createQuery(sql.toString(), Assets.class).setParameter("assetsName", assetsName)
				.setParameter("companiesCode", companiesCode)
				.getSingleResult();
		return assets;
	}

	@Override
	public List<Assets> findByItemsCodeCompany(String companiesCode,String itemsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE i.itemsCode = :itemsCode ");
		sql.append(" AND c.companiesCode = :companiesCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("itemsCode", itemsCode)
				.setParameter("companiesCode", companiesCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByBrandsCodeCompany(String companiesCode,String brandsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE ib.itemsBrandsCode= :brandsCode ");
		sql.append(" AND c.companiesCode= :companiesCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("brandsCode", brandsCode)
				.setParameter("companiesCode", companiesCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByItemsTypesCodeCompany(String companiesCode,String itemsTypesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE it.itemsTypesCode = :itemsTypesCode ");
		sql.append(" AND c.companiesCode = :companiesCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("itemsTypesCode", itemsTypesCode)
				.setParameter("companiesCode", companiesCode)
				.getResultList();
		return listAssets;

	}

	@Override
	public List<Assets> findByStatusesAssetsCodeCompany(String companiesCode,String statusesAssetsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE sa.statusesAssetsCode = :statusesAssetsCode ");
		sql.append(" AND c.companiesCode = :companiesCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("statusesAssetsCode", statusesAssetsCode)
				.setParameter("companiesCode", companiesCode)
				.getResultList();
		return listAssets;

	}

	@Override
	public List<Assets> findByStatusesInOutCodeCompany(String companiesCode,String statusesInOutCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE sio.statusesInOutCode= :statusesInOutCode ");
		sql.append(" AND c.companiesCode =:companiesCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("statusesInOutCode", statusesInOutCode)
				.setParameter("companiesCode", companiesCode)
				.getResultList();
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
	public List<Assets> findByReqCompany(String itemsCode, String companiesCode, String statusesAssetsCode, String statusesInOutCode, Integer total) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE i.itemsCode= :itemsCode AND ");
		sql.append(" sa.statusesAssetsCode= :statusesAssetsCode AND ");
		sql.append(" sio.statusesInOutCode= :statusesInOutCode AND ");
		sql.append(" c.companiesCode=:companiesCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("itemsCode", itemsCode)
				.setParameter("statusesAssetsCode", statusesAssetsCode)
				.setParameter("statusesInOutCode", statusesInOutCode)
				.setParameter("companiesCode", companiesCode)
				.setMaxResults(total)
				.getResultList();
		return listAssets;
	}

	@Override
	public Integer countData() throws Exception {
		Object resultQuery = createNativeQuery("SELECT COUNT(a.id) FROM Assets a").getSingleResult();
		if(resultQuery==null) {
			return 0;
		}
		BigInteger results = new BigInteger(resultQuery.toString());
		Integer resultsInteger = results.intValue();
		return resultsInteger;
	}

	@Override
	public List<Assets> getExpiredAssetsCompany(String companiesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE a.assetsExpired <= DATE(NOW()) ");
		sql.append(" AND c.companiesCode= :companiesCode ");
		sql.append(" AND it.itemsTypesName = 'Licenses' ");
		sql.append(" ORDER BY a.assetsExpired DESC ");
		
		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("companiesCode", companiesCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> getNewAssetsCompany(String companiesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE c.companiesCode= :companiesCode ");
		sql.append(" ORDER BY a.createdAt DESC ");
		
		List<Assets> assets = createQuery(sql.toString(), Assets.class)
				.setParameter("companiesCode", companiesCode)
				.setMaxResults(1)
				.getResultList();
		return assets;
	}

	@Override
	public List<Assets> getTop5AssetsDeployCompany(String companiesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE sio.statusesInOutCode = 'COUT' ");
		sql.append(" AND sa.statusesAssetsCode = 'UNDEP' ");
		sql.append(" AND c.companiesCode= :companiesCode ");
		sql.append(" ORDER BY a.updatedAt DESC ");
		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("companiesCode", companiesCode)
				.setMaxResults(5)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByStatAssetsInOutCompany(String companiesCode,String statusesAssetsCode, String statusesInOutCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE sio.statusesInOutCode = :statInCode ");
		sql.append(" AND sa.statusesAssetsCode = :statAssCode ");
		sql.append(" AND c.companiesCode= :companiesCode ");
		sql.append(" ORDER BY a.updatedAt DESC ");
		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("statInCode", statusesInOutCode)
				.setParameter("statAssCode", statusesAssetsCode)
				.setParameter("companiesCode", companiesCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByCompaniesCode(String companiesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE c.companiesCode= :companiesCode ");
		sql.append(" ORDER BY a.createdAt DESC ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("companiesCode", companiesCode).getResultList();
		return listAssets;
	}

	@Override
	public Assets findByAssetsName(String assetsName) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE a.assetsName = :assetsName ");
		sql.append(" ORDER BY a.createdAt DESC ");

		Assets assets = createQuery(sql.toString(), Assets.class).setParameter("assetsName", assetsName)
				.getSingleResult();
		return assets;
	}

	@Override
	public List<Assets> findByStatAssetsInOut(String statusesAssetsCode, String statusesInOutCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE sa.statusesAssetsCode = :statusesAssetsCode ");
		sql.append(" AND sio.statusesInOutCode = :statusesInOutCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setParameter("statusesAssetsCode", statusesAssetsCode)
				.setParameter("statusesInOutCode", statusesInOutCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByItemsCode(String itemsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE i.itemsCode = :itemsCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("itemsCode", itemsCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByBrandsCode(String brandsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE ib.itemsBrandsCode = :itemsBrandsCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("itemsBrandsCode", brandsCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByItemsTypesCode(String itemsTypesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE it.itemsTypesCode = :itemsTypesCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("itemsTypesCode", itemsTypesCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByStatusesAssetsCode(String statusesAssetsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE sa.statusesAssetsCode = :statusesAssetsCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("statusesAssetsCode", statusesAssetsCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> findByStatusesInOutCode(String statusesInOutCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE sio.statusesInOutCode = :statusesInOutCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("statusesInOutCode", statusesInOutCode)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> getExpiredAssets() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE a.assetsExpired <= DATE(NOW()) ");
		sql.append(" AND it.itemsTypesName = 'Licenses' ");
		sql.append(" ORDER BY a.assetsExpired DESC ");
		
		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> getTop5AssetsDeploy() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE sio.statusesInOutCode = 'COUT' ");
		sql.append(" AND sa.statusesAssetsCode = 'UNDEP' ");
		sql.append(" ORDER BY a.updatedAt DESC ");
		List<Assets> listAssets = createQuery(sql.toString(), Assets.class)
				.setMaxResults(5)
				.getResultList();
		return listAssets;
	}

	@Override
	public List<Assets> getNewAssets() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" ORDER BY a.createdAt DESC ");
		
		List<Assets> assets = createQuery(sql.toString(), Assets.class)
				.setMaxResults(1)
				.getResultList();
		return assets;
	}

	@Override
	public List<Assets> findByReq(String itemsCode, String statusesAssetsCode, String statusesInOutCode, Integer total)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a ");
		sql.append(" FROM Assets AS a ");
		sql.append(" INNER JOIN FETCH a.items i ");
		sql.append(" INNER JOIN FETCH a.companies c ");
		sql.append(" INNER JOIN FETCH i.files as f");
		sql.append(" INNER JOIN FETCH i.itemsTypes it ");
		sql.append(" INNER JOIN FETCH i.itemsBrands ib ");
		sql.append(" INNER JOIN FETCH a.invoices ");
		sql.append(" INNER JOIN FETCH a.statusesAssets sa ");
		sql.append(" INNER JOIN FETCH a.statusesInOut sio ");
		sql.append(" WHERE i.itemsCode= :itemsCode AND ");
		sql.append(" sa.statusesAssetsCode= :statusesAssetsCode AND ");
		sql.append(" sio.statusesInOutCode= :statusesInOutCode ");

		List<Assets> listAssets = createQuery(sql.toString(), Assets.class).setParameter("itemsCode", itemsCode)
				.setParameter("statusesAssetsCode", statusesAssetsCode)
				.setParameter("statusesInOutCode", statusesInOutCode)
				.setMaxResults(total)
				.getResultList();
		return listAssets;
	}
}
