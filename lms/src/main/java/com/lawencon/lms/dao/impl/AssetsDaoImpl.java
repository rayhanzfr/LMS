package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.bouncycastle.asn1.cms.TimeStampAndCRL;
import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.AssetsDao;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Invoices;
import com.lawencon.lms.model.Items;
import com.lawencon.lms.model.ItemsBrands;
import com.lawencon.lms.model.ItemsTypes;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.StatusesInOut;

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

		Assets assets = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					" SELECT a.id, invoices.invoices_code, i.items_name, ib.items_brands_name, a.assets_name, sa.statuses_assets_name, sio.statuses_in_out_name, a.assets_expired ");
			sql.append(" FROM assets as a ");
			sql.append(" INNER JOIN items as i ON i.id = a.items_id ");
			sql.append(" INNER JOIN items_types as it ON it.id = a.items_types_id ");
			sql.append(" INNER JOIN items_brands as ib ON ib.id = a.items_brands_id ");
			sql.append(" INNER JOIN invoices ON invoices.id = a.invoices_id ");
			sql.append(" INNER JOIN statuses_assets as sa ON sa.id = a.statuses_assets_id ");
			sql.append(" INNER JOIN statuses_in_out as sio ON sio.id = a.statuses_in_out_id ");
			sql.append(" WHERE a.assets_name = :assetsName ");

			Object resultQuery = createNativeQuery(sql.toString()).setParameter("assetsName", assetsName)
					.getSingleResult();

			if (resultQuery != null) {
				Object[] obj = (Object[]) resultQuery;
				assets = new Assets();
				assets.setId(obj[0].toString());

				Invoices invoices = new Invoices();
				invoices.setInvoicesCode(obj[1].toString());
				assets.setInvoices(invoices);

				Items items = new Items();
				items.setItemsName(obj[2].toString());

				ItemsBrands itemsBrands = new ItemsBrands();
				itemsBrands.setItemsBrandsName(obj[3].toString());
				items.setItemsBrands(itemsBrands);

				ItemsTypes itemsTypes = new ItemsTypes();
				itemsTypes.setItemsTypesName(obj[4].toString());
				items.setItemsTypes(itemsTypes);

				assets.setItems(items);
				assets.setAssetsName(obj[5].toString());

				StatusesAssets statusesAssets = new StatusesAssets();
				statusesAssets.setStatusesAssetsName(obj[6].toString());
				assets.setStatusesAssets(statusesAssets);

				StatusesInOut statusesInOut = new StatusesInOut();
				statusesInOut.setStatusesInOutName(obj[7].toString());
				assets.setStatusesInOut(statusesInOut);

				if (obj[8] != null) {
					assets.setAssetsExpired(Timestamp.valueOf(obj[8].toString()).toLocalDateTime().toLocalDate());
				}
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return assets;
	}

	@Override
	public List<Assets> findByItemsCode(String itemsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT a.id, invoices.invoices_code, i.items_name, ib.items_brands_name, a.assets_name, sa.statuses_assets_name, sio.statuses_in_out_name, a.assets_expired, a.created_by, a.created_at,a.version ");
		sql.append(" FROM assets as a ");
		sql.append(" INNER JOIN items as i ON i.id = a.items_id ");
		sql.append(" INNER JOIN items_types as it ON it.id = a.items_types_id ");
		sql.append(" INNER JOIN items_brands as ib ON ib.id = a.items_brands_id ");
		sql.append(" INNER JOIN invoices ON invoices.id = a.invoices_id ");
		sql.append(" INNER JOIN statuses_assets as sa ON sa.id = a.statuses_assets_id ");
		sql.append(" INNER JOIN statuses_in_out as sio ON sio.id = a.statuses_in_out_id ");
		sql.append(" WHERE i.items_code= :ItemsCode ");

		List<?> resultQuery = createNativeQuery(sql.toString()).setParameter("assetsName", itemsCode).getResultList();
		List<Assets> listAssets = new ArrayList<Assets>();
		if (resultQuery != null) {
			resultQuery.forEach(rs -> {
				Object[] obj = (Object[]) rs;
				Assets assets = new Assets();
				assets.setId(obj[0].toString());

				Invoices invoices = new Invoices();
				invoices.setInvoicesCode(obj[1].toString());
				assets.setInvoices(invoices);

				Items items = new Items();
				items.setItemsName(obj[2].toString());

				ItemsBrands itemsBrands = new ItemsBrands();
				itemsBrands.setItemsBrandsName(obj[3].toString());
				items.setItemsBrands(itemsBrands);

				ItemsTypes itemsTypes = new ItemsTypes();
				itemsTypes.setItemsTypesName(obj[4].toString());
				items.setItemsTypes(itemsTypes);

				assets.setItems(items);
				assets.setAssetsName(obj[5].toString());

				StatusesAssets statusesAssets = new StatusesAssets();
				statusesAssets.setStatusesAssetsName(obj[6].toString());
				assets.setStatusesAssets(statusesAssets);

				StatusesInOut statusesInOut = new StatusesInOut();
				statusesInOut.setStatusesInOutName(obj[7].toString());
				assets.setStatusesInOut(statusesInOut);

				if (obj[8] != null) {
					assets.setAssetsExpired(Timestamp.valueOf(obj[8].toString()).toLocalDateTime().toLocalDate());
				}

				assets.setCreatedBy(obj[9].toString());
				assets.setCreatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
				assets.setVersion(Integer.valueOf(obj[11].toString()));

				listAssets.add(assets);
			});

		}
		return listAssets;
	}

	@Override
	public List<Assets> findByBrandsCode(String brandsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT a.id, invoices.invoices_code, i.items_name, ib.items_brands_name, a.assets_name, sa.statuses_assets_name, sio.statuses_in_out_name, a.assets_expired, a.created_by, a.created_at,a.version ");
		sql.append(" FROM assets as a ");
		sql.append(" INNER JOIN items as i ON i.id = a.items_id ");
		sql.append(" INNER JOIN items_types as it ON it.id = a.items_types_id ");
		sql.append(" INNER JOIN items_brands as ib ON ib.id = a.items_brands_id ");
		sql.append(" INNER JOIN invoices ON invoices.id = a.invoices_id ");
		sql.append(" INNER JOIN statuses_assets as sa ON sa.id = a.statuses_assets_id ");
		sql.append(" INNER JOIN statuses_in_out as sio ON sio.id = a.statuses_in_out_id ");
		sql.append(" WHERE ib.items_brands_code= :brandsCode ");

		List<?> resultQuery = createNativeQuery(sql.toString()).setParameter("brandsCode", brandsCode).getResultList();
		List<Assets> listAssets = new ArrayList<Assets>();
		if (resultQuery != null) {
			resultQuery.forEach(rs -> {
				Object[] obj = (Object[]) rs;
				Assets assets = new Assets();
				assets.setId(obj[0].toString());

				Invoices invoices = new Invoices();
				invoices.setInvoicesCode(obj[1].toString());
				assets.setInvoices(invoices);

				Items items = new Items();
				items.setItemsName(obj[2].toString());

				ItemsBrands itemsBrands = new ItemsBrands();
				itemsBrands.setItemsBrandsName(obj[3].toString());
				items.setItemsBrands(itemsBrands);

				ItemsTypes itemsTypes = new ItemsTypes();
				itemsTypes.setItemsTypesName(obj[4].toString());
				items.setItemsTypes(itemsTypes);

				assets.setItems(items);
				assets.setAssetsName(obj[5].toString());

				StatusesAssets statusesAssets = new StatusesAssets();
				statusesAssets.setStatusesAssetsName(obj[6].toString());
				assets.setStatusesAssets(statusesAssets);

				StatusesInOut statusesInOut = new StatusesInOut();
				statusesInOut.setStatusesInOutName(obj[7].toString());
				assets.setStatusesInOut(statusesInOut);

				if (obj[8] != null) {
					assets.setAssetsExpired(Timestamp.valueOf(obj[8].toString()).toLocalDateTime().toLocalDate());
				}

				assets.setCreatedBy(obj[9].toString());
				assets.setCreatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
				assets.setVersion(Integer.valueOf(obj[11].toString()));

				listAssets.add(assets);
			});

		}
		return listAssets;
	}

	@Override
	public List<Assets> findByItemsTypesCode(String itemsTypesCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT a.id, invoices.invoices_code, i.items_name, ib.items_brands_name, a.assets_name, sa.statuses_assets_name, sio.statuses_in_out_name, a.assets_expired, a.created_by, a.created_at,a.version ");
		sql.append(" FROM assets as a ");
		sql.append(" INNER JOIN items as i ON i.id = a.items_id ");
		sql.append(" INNER JOIN items_types as it ON it.id = a.items_types_id ");
		sql.append(" INNER JOIN items_brands as ib ON ib.id = a.items_brands_id ");
		sql.append(" INNER JOIN invoices ON invoices.id = a.invoices_id ");
		sql.append(" INNER JOIN statuses_assets as sa ON sa.id = a.statuses_assets_id ");
		sql.append(" INNER JOIN statuses_in_out as sio ON sio.id = a.statuses_in_out_id ");
		sql.append(" WHERE it.items_types_code= :itemsTypesCode ");

		List<?> resultQuery = createNativeQuery(sql.toString()).setParameter("brandsCode", itemsTypesCode)
				.getResultList();
		List<Assets> listAssets = new ArrayList<Assets>();
		if (resultQuery != null) {
			resultQuery.forEach(rs -> {
				Object[] obj = (Object[]) rs;
				Assets assets = new Assets();
				assets.setId(obj[0].toString());

				Invoices invoices = new Invoices();
				invoices.setInvoicesCode(obj[1].toString());
				assets.setInvoices(invoices);

				Items items = new Items();
				items.setItemsName(obj[2].toString());

				ItemsBrands itemsBrands = new ItemsBrands();
				itemsBrands.setItemsBrandsName(obj[3].toString());
				items.setItemsBrands(itemsBrands);

				ItemsTypes itemsTypes = new ItemsTypes();
				itemsTypes.setItemsTypesName(obj[4].toString());
				items.setItemsTypes(itemsTypes);

				assets.setItems(items);
				assets.setAssetsName(obj[5].toString());

				StatusesAssets statusesAssets = new StatusesAssets();
				statusesAssets.setStatusesAssetsName(obj[6].toString());
				assets.setStatusesAssets(statusesAssets);

				StatusesInOut statusesInOut = new StatusesInOut();
				statusesInOut.setStatusesInOutName(obj[7].toString());
				assets.setStatusesInOut(statusesInOut);

				if (obj[8] != null) {
					assets.setAssetsExpired(Timestamp.valueOf(obj[8].toString()).toLocalDateTime().toLocalDate());
				}

				assets.setCreatedBy(obj[9].toString());
				assets.setCreatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
				assets.setVersion(Integer.valueOf(obj[11].toString()));

				listAssets.add(assets);
			});

		}
		return listAssets;

	}

	@Override
	public List<Assets> findByStatusesAssetsCode(String statusesAssetsCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT a.id, invoices.invoices_code, i.items_name, ib.items_brands_name, a.assets_name, sa.statuses_assets_name, sio.statuses_in_out_name, a.assets_expired, a.created_by, a.created_at,a.version ");
		sql.append(" FROM assets as a ");
		sql.append(" INNER JOIN items as i ON i.id = a.items_id ");
		sql.append(" INNER JOIN items_types as it ON it.id = a.items_types_id ");
		sql.append(" INNER JOIN items_brands as ib ON ib.id = a.items_brands_id ");
		sql.append(" INNER JOIN invoices ON invoices.id = a.invoices_id ");
		sql.append(" INNER JOIN statuses_assets as sa ON sa.id = a.statuses_assets_id ");
		sql.append(" INNER JOIN statuses_in_out as sio ON sio.id = a.statuses_in_out_id ");
		sql.append(" WHERE sa.statuses_assets_code= :statusesAssetsCode ");

		List<?> resultQuery = createNativeQuery(sql.toString()).setParameter("brandsCode", statusesAssetsCode)
				.getResultList();
		List<Assets> listAssets = new ArrayList<Assets>();
		if (resultQuery != null) {
			resultQuery.forEach(rs -> {
				Object[] obj = (Object[]) rs;
				Assets assets = new Assets();
				assets.setId(obj[0].toString());

				Invoices invoices = new Invoices();
				invoices.setInvoicesCode(obj[1].toString());
				assets.setInvoices(invoices);

				Items items = new Items();
				items.setItemsName(obj[2].toString());

				ItemsBrands itemsBrands = new ItemsBrands();
				itemsBrands.setItemsBrandsName(obj[3].toString());
				items.setItemsBrands(itemsBrands);

				ItemsTypes itemsTypes = new ItemsTypes();
				itemsTypes.setItemsTypesName(obj[4].toString());
				items.setItemsTypes(itemsTypes);

				assets.setItems(items);
				assets.setAssetsName(obj[5].toString());

				StatusesAssets statusesAssets = new StatusesAssets();
				statusesAssets.setStatusesAssetsName(obj[6].toString());
				assets.setStatusesAssets(statusesAssets);

				StatusesInOut statusesInOut = new StatusesInOut();
				statusesInOut.setStatusesInOutName(obj[7].toString());
				assets.setStatusesInOut(statusesInOut);

				if (obj[8] != null) {
					assets.setAssetsExpired(Timestamp.valueOf(obj[8].toString()).toLocalDateTime().toLocalDate());
				}

				assets.setCreatedBy(obj[9].toString());
				assets.setCreatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
				assets.setVersion(Integer.valueOf(obj[11].toString()));

				listAssets.add(assets);
			});

		}
		return listAssets;

	}

	@Override
	public List<Assets> findByStatusesInOutCode(String statusesInOutCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT a.id, invoices.invoices_code, i.items_name, ib.items_brands_name, a.assets_name, sa.statuses_assets_name, sio.statuses_in_out_name, a.assets_expired, a.created_by, a.created_at,a.version ");
		sql.append(" FROM assets as a ");
		sql.append(" INNER JOIN items as i ON i.id = a.items_id ");
		sql.append(" INNER JOIN items_types as it ON it.id = a.items_types_id ");
		sql.append(" INNER JOIN items_brands as ib ON ib.id = a.items_brands_id ");
		sql.append(" INNER JOIN invoices ON invoices.id = a.invoices_id ");
		sql.append(" INNER JOIN statuses_assets as sa ON sa.id = a.statuses_assets_id ");
		sql.append(" INNER JOIN statuses_in_out as sio ON sio.id = a.statuses_in_out_id ");
		sql.append(" WHERE sio.statuses_in_out_code= :statusesInOutCode ");

		List<?> resultQuery = createNativeQuery(sql.toString()).setParameter("statusesInOutCode", statusesInOutCode)
				.getResultList();
		List<Assets> listAssets = new ArrayList<Assets>();
		if (resultQuery != null) {
			resultQuery.forEach(rs -> {
				Object[] obj = (Object[]) rs;
				Assets assets = new Assets();
				assets.setId(obj[0].toString());

				Invoices invoices = new Invoices();
				invoices.setInvoicesCode(obj[1].toString());
				assets.setInvoices(invoices);

				Items items = new Items();
				items.setItemsName(obj[2].toString());

				ItemsBrands itemsBrands = new ItemsBrands();
				itemsBrands.setItemsBrandsName(obj[3].toString());
				items.setItemsBrands(itemsBrands);

				ItemsTypes itemsTypes = new ItemsTypes();
				itemsTypes.setItemsTypesName(obj[4].toString());
				items.setItemsTypes(itemsTypes);

				assets.setItems(items);
				assets.setAssetsName(obj[5].toString());

				StatusesAssets statusesAssets = new StatusesAssets();
				statusesAssets.setStatusesAssetsName(obj[6].toString());
				assets.setStatusesAssets(statusesAssets);

				StatusesInOut statusesInOut = new StatusesInOut();
				statusesInOut.setStatusesInOutName(obj[7].toString());
				assets.setStatusesInOut(statusesInOut);

				if (obj[8] != null) {
					assets.setAssetsExpired(Timestamp.valueOf(obj[8].toString()).toLocalDateTime().toLocalDate());
				}

				assets.setCreatedBy(obj[9].toString());
				assets.setCreatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
				assets.setVersion(Integer.valueOf(obj[11].toString()));

				listAssets.add(assets);
			});

		}
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

}
