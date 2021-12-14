package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.AssetsDao;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.service.AssetsService;
import com.lawencon.lms.service.InvoicesService;
import com.lawencon.lms.service.ItemsBrandsService;
import com.lawencon.lms.service.ItemsService;
import com.lawencon.lms.service.ItemsTypesService;
import com.lawencon.lms.service.StatusesAssetsService;
import com.lawencon.lms.service.StatusesInOutService;

public class AssetsServiceImpl extends BaseServiceImpl implements AssetsService {

	@Autowired
	private AssetsDao assetsDao;
	
	@Autowired
	private ItemsService itemService;
	
	@Autowired
	private ItemsTypesService itemsTypesService;
	
	@Autowired
	private ItemsBrandsService itemsBrandsService;
	
	@Autowired
	private StatusesAssetsService statusesAssetsService;
	
	@Autowired
	private StatusesInOutService statusesInOutService;
	
	@Autowired
	private InvoicesService invoicesService;
	
	@Override
	public List<Assets> findAll() throws Exception {
		return assetsDao.findAll();
	}

	@Override
	public Assets findById(String id) throws Exception {
		return assetsDao.findById(id);
	}

	@Override
	public Assets findByAssetsName(String assetsName) throws Exception {
		return assetsDao.findByAssetsName(assetsName);
	}

	@Override
	public List<Assets> findByItemsCode(String itemsCode) throws Exception {
		return assetsDao.findByItemsCode(itemsCode);
	}

	@Override
	public List<Assets> findByBrandsCode(String brandsCode) throws Exception {
		return assetsDao.findByBrandsCode(brandsCode);
	}

	@Override
	public List<Assets> findByItemsTypesCode(String itemsTypesCode) throws Exception {
		return assetsDao.findByItemsTypesCode(itemsTypesCode);
	}

	@Override
	public List<Assets> findByStatusesAssetsCode(String statusesAssetsCode) throws Exception {
		return assetsDao.findByStatusesAssetsCode(statusesAssetsCode);
	}

	@Override
	public List<Assets> findByStatusesInOutCode(String statusesInOutCode) throws Exception {
		return assetsDao.findByStatusesInOutCode(statusesInOutCode);
	}

	@Override
	public Assets save(Assets assets) throws Exception {
		return null;
	}

	@Override
	public Assets update(Assets assets) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return removeById(id);
	}

	@Override
	public Assets updateStatusAssets(Assets assets) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assets updateStatusInOut(Assets assets) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
