package com.lawencon.lms.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.AssetsDao;
import com.lawencon.lms.dao.InvoicesDao;
import com.lawencon.lms.dao.ItemsBrandsDao;
import com.lawencon.lms.dao.ItemsDao;
import com.lawencon.lms.dao.ItemsTypesDao;
import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.dao.StatusesInOutDao;
import com.lawencon.lms.dto.assets.AssetsDataDto;
import com.lawencon.lms.dto.assets.GetAllAssetsDto;
import com.lawencon.lms.dto.assets.GetByIdAssetsDto;
import com.lawencon.lms.dto.assets.SaveAssetsDataDto;
import com.lawencon.lms.dto.assets.SaveAssetsReqDto;
import com.lawencon.lms.dto.assets.SaveAssetsResDto;
import com.lawencon.lms.dto.assets.UpdateAssetsDataDto;
import com.lawencon.lms.dto.assets.UpdateAssetsReqDto;
import com.lawencon.lms.dto.assets.UpdateAssetsResDto;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Invoices;
import com.lawencon.lms.model.Items;
import com.lawencon.lms.model.ItemsBrands;
import com.lawencon.lms.model.ItemsTypes;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.StatusesInOut;
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
	private ItemsDao itemsDao;
	
	@Autowired
	private ItemsTypesDao itemsTypesDao;
	
	@Autowired
	private ItemsBrandsDao itemsBrandsDao;
	
	@Autowired
	private StatusesAssetsDao statusesAssetsDao;
	
	@Autowired
	private StatusesInOutDao statusesInOutDao;
	
	@Autowired
	private InvoicesDao invoicesDao;
	
	
	private AssetsDataDto convert(Assets assets) {
		AssetsDataDto data = new AssetsDataDto();
		data.setId(assets.getId());
		data.setItemsId(assets.getItems().getId());
		data.setItemsName(assets.getItems().getItemsName());
		data.setInvoicesId(assets.getInvoices().getId());
		data.setInvoicesCode(assets.getInvoices().getInvoicesCode());
		data.setAssetsName(assets.getAssetsName());
		data.setStatusesAssetsId(assets.getStatusesAssets().getId());
		data.setStatusesAssetsName(assets.getStatusesAssets().getStatusesAssetsName());
		data.setStatusesInOutId(assets.getStatusesInOut().getId());
		data.setStatusesInOutName(assets.getStatusesInOut().getStatusesInOutName());
		data.setAssetsExpired(assets.getAssetsExpired());
		data.setCreatedBy(assets.getCreatedBy());
		data.setCreatedAt(assets.getCreatedAt());
		data.setUpdatedBy(assets.getUpdatedBy());
		data.setUpdatedAt(assets.getUpdatedAt());
		data.setIsActive(assets.getIsActive());
		data.setVersion(assets.getVersion());
		return data;
	}

	@Override
	public GetAllAssetsDto findAll() throws Exception {
		GetAllAssetsDto assetsAll = new GetAllAssetsDto();
		List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
		List<Assets> assets = assetsDao.findAll();
		assets.forEach(asset->{
			AssetsDataDto data = convert(asset);
			listAssets.add(data);
		});
		assetsAll.setData(listAssets);
		return assetsAll;
	}



	@Override
	public GetByIdAssetsDto findById(String id) throws Exception {
		Assets asset = assetsDao.findById(id);
		GetByIdAssetsDto getAssets = new GetByIdAssetsDto();
		AssetsDataDto data = convert(asset);
		getAssets.setData(data);
		return getAssets;
	}



	@Override
	public GetByIdAssetsDto findByAssetsName(String assetsName) throws Exception {
		Assets asset = assetsDao.findByAssetsName(assetsName);
		GetByIdAssetsDto getAssets = new GetByIdAssetsDto();
		AssetsDataDto data = convert(asset);
		getAssets.setData(data);
		return getAssets;
	}



	@Override
	public GetAllAssetsDto findByItemsCode(String itemsCode) throws Exception {
		GetAllAssetsDto assetsAll = new GetAllAssetsDto();
		List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
		List<Assets> assets = assetsDao.findByItemsCode(itemsCode);
		assets.forEach(asset->{
			AssetsDataDto data = convert(asset);
			listAssets.add(data);
		});
		assetsAll.setData(listAssets);
		return assetsAll;
	}



	@Override
	public GetAllAssetsDto findByBrandsCode(String brandsCode) throws Exception {
		GetAllAssetsDto assetsAll = new GetAllAssetsDto();
		List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
		List<Assets> assets = assetsDao.findByBrandsCode(brandsCode);
		assets.forEach(asset->{
			AssetsDataDto data = convert(asset);
			listAssets.add(data);
		});
		assetsAll.setData(listAssets);
		return assetsAll;
	}



	@Override
	public GetAllAssetsDto findByItemsTypesCode(String itemsTypesCode) throws Exception {
		GetAllAssetsDto assetsAll = new GetAllAssetsDto();
		List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
		List<Assets> assets = assetsDao.findByItemsTypesCode(itemsTypesCode);
		assets.forEach(asset->{
			AssetsDataDto data = convert(asset);
			listAssets.add(data);
		});
		assetsAll.setData(listAssets);
		return assetsAll;
	}



	@Override
	public GetAllAssetsDto findByStatusesAssetsCode(String statusesAssetsCode) throws Exception {
		GetAllAssetsDto assetsAll = new GetAllAssetsDto();
		List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
		List<Assets> assets = assetsDao.findByStatusesAssetsCode(statusesAssetsCode);
		assets.forEach(asset->{
			AssetsDataDto data = convert(asset);
			listAssets.add(data);
		});
		assetsAll.setData(listAssets);
		return assetsAll;
	}



	@Override
	public GetAllAssetsDto findByStatusesInOutCode(String statusesInOutCode) throws Exception {
		GetAllAssetsDto assetsAll = new GetAllAssetsDto();
		List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
		List<Assets> assets = assetsDao.findByStatusesInOutCode(statusesInOutCode);
		assets.forEach(asset->{
			AssetsDataDto data = convert(asset);
			listAssets.add(data);
		});
		assetsAll.setData(listAssets);
		return assetsAll;
	}



	@Override
	public SaveAssetsResDto save(SaveAssetsReqDto saveAssetsReqDto) throws Exception {
		Items item = itemsDao.findByCode(saveAssetsReqDto.getItemsCode());
		Invoices invoice = invoicesDao.findByCode(saveAssetsReqDto.getInvoicesCode());
		StatusesAssets statusesAssets = statusesAssetsDao.findByCode(saveAssetsReqDto.getStatusesAssetsCode());
		StatusesInOut statusesInOut = statusesInOutDao.findByCode(saveAssetsReqDto.getStatusesInOutCode());
		
		Assets save = new Assets();
		save.setItems(item);
		save.setInvoices(invoice);
		save.setAssetsName(saveAssetsReqDto.getAssetsName());
		save.setStatusesAssets(statusesAssets);
		save.setStatusesInOut(statusesInOut);
		save.setAssetsExpired(LocalDate.parse(saveAssetsReqDto.getAssetsExpired()));
		save.setCreatedBy(saveAssetsReqDto.getCreatedBy());
		save.setIsActive(saveAssetsReqDto.getIsActive());
		begin();
		Assets result = assetsDao.saveOrUpdate(save);
		
		SaveAssetsDataDto resDataDto = new SaveAssetsDataDto();
		resDataDto.setId(result.getId());
		
		SaveAssetsResDto resDto = new SaveAssetsResDto();
		resDto.setData(resDataDto);
		resDto.setMessage("SUCCESS");
		commit();
		return resDto;
	}



	@Override
	public UpdateAssetsResDto update(UpdateAssetsReqDto updateAssetsReqDto) throws Exception {
		Items item = itemsDao.findByCode(updateAssetsReqDto.getItemsCode());
		Invoices invoice = invoicesDao.findByCode(updateAssetsReqDto.getInvoicesCode());
		StatusesAssets statusesAssets = statusesAssetsDao.findByCode(updateAssetsReqDto.getStatusesAssetsCode());
		StatusesInOut statusesInOut = statusesInOutDao.findByCode(updateAssetsReqDto.getStatusesInOutCode());
		
		Assets save = new Assets();
		save.setItems(item);
		save.setInvoices(invoice);
		save.setAssetsName(updateAssetsReqDto.getAssetsName());
		save.setStatusesAssets(statusesAssets);
		save.setStatusesInOut(statusesInOut);
		save.setAssetsExpired(LocalDate.parse(updateAssetsReqDto.getAssetsExpired()));
		save.setUpdatedBy(updateAssetsReqDto.getUpdatedBy());
		save.setIsActive(updateAssetsReqDto.getIsActive());
		save.setVersion(updateAssetsReqDto.getVersion());
		begin();
		Assets result = assetsDao.saveOrUpdate(save);
		
		UpdateAssetsDataDto resDataDto = new UpdateAssetsDataDto();
		resDataDto.setId(result.getId());
		
		UpdateAssetsResDto resDto = new UpdateAssetsResDto();
		resDto.setData(resDataDto);
		resDto.setMessage("SUCCESS");
		commit();
		return resDto;
	}



	@Override
	public UpdateAssetsResDto updateStatusAssets(UpdateAssetsReqDto updateAssetsReqDto) throws Exception {
		StatusesAssets statusesAssets = statusesAssetsDao.findByCode(updateAssetsReqDto.getStatusesAssetsCode());
		
		Assets save = new Assets();
		save.setStatusesAssets(statusesAssets);
		save.setUpdatedBy(updateAssetsReqDto.getUpdatedBy());
		save.setIsActive(updateAssetsReqDto.getIsActive());
		save.setVersion(updateAssetsReqDto.getVersion());
		begin();
		Assets result = assetsDao.saveOrUpdate(save);
		
		UpdateAssetsDataDto resDataDto = new UpdateAssetsDataDto();
		resDataDto.setId(result.getId());
		
		UpdateAssetsResDto resDto = new UpdateAssetsResDto();
		resDto.setData(resDataDto);
		resDto.setMessage("SUCCESS");
		commit();
		return resDto;
	}



	@Override
	public UpdateAssetsResDto updateStatusInOut(UpdateAssetsReqDto updateAssetsReqDto) throws Exception {
		StatusesInOut statusesInOut = statusesInOutDao.findByCode(updateAssetsReqDto.getStatusesInOutCode());
		
		Assets save = new Assets();
		save.setStatusesInOut(statusesInOut);
		save.setUpdatedBy(updateAssetsReqDto.getUpdatedBy());
		save.setIsActive(updateAssetsReqDto.getIsActive());
		save.setVersion(updateAssetsReqDto.getVersion());
		begin();
		Assets result = assetsDao.saveOrUpdate(save);
		
		UpdateAssetsDataDto resDataDto = new UpdateAssetsDataDto();
		resDataDto.setId(result.getId());
		
		UpdateAssetsResDto resDto = new UpdateAssetsResDto();
		resDto.setData(resDataDto);
		resDto.setMessage("SUCCESS");
		commit();
		return resDto;
	}



	@Override
	public Boolean removeById(String id) throws Exception {
		return assetsDao.removeById(id);
	}



}
