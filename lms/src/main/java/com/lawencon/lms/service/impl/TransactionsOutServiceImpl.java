package com.lawencon.lms.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.AssetsDao;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.dao.LocationsDao;
import com.lawencon.lms.dao.TransactionsDetailOutDao;
import com.lawencon.lms.dao.TransactionsOutDao;
import com.lawencon.lms.dto.transactionsout.GetAllTransactionsOutResDto;
import com.lawencon.lms.dto.transactionsout.GetByTransactionsOutCodeResDto;
import com.lawencon.lms.dto.transactionsout.GetByTransactionsOutIdResDto;
import com.lawencon.lms.dto.transactionsout.GetTransactionsOutDataDto;
import com.lawencon.lms.dto.transactionsout.SaveFullTransactionsOutReqDto;
import com.lawencon.lms.dto.transactionsout.SaveFullTransactionsOutResDto;
import com.lawencon.lms.dto.transactionsout.SaveTransactionsDetailsOutReqDto;
import com.lawencon.lms.dto.transactionsout.SaveTransactionsDetailsOutResDto;
import com.lawencon.lms.dto.transactionsout.SaveTransactionsOutReqDto;
import com.lawencon.lms.dto.transactionsout.SaveTransactionsOutResDto;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.model.TransactionsDetailOut;
import com.lawencon.lms.model.TransactionsOut;
import com.lawencon.lms.service.TransactionsOutService;

@Service
public class TransactionsOutServiceImpl extends BaseServiceImpl implements TransactionsOutService {

	@Autowired
	private TransactionsOutDao transactionsOutDao;
	
	@Autowired
	private LocationsDao locationsDao;
	
	@Autowired
	private EmployeesDao employeesDao;
	
	@Autowired
	private AssetsDao assetsDao;
	
	@Autowired
	private TransactionsDetailOutDao transactionsDetailOutDao;

	@Override
	public SaveFullTransactionsOutResDto save(SaveFullTransactionsOutReqDto itemsReq) throws Exception {
		SaveFullTransactionsOutResDto saveFullTransactionsOutResDto = new SaveFullTransactionsOutResDto();
		SaveTransactionsOutResDto headerRes = new SaveTransactionsOutResDto();
		SaveFullTransactionsOutReqDto saveFullTransactionsOutReqDto = (SaveFullTransactionsOutReqDto) itemsReq;
		SaveTransactionsOutReqDto saveTransactionsOutReqDto = saveFullTransactionsOutReqDto.getSaveTransactionsOutReqDto();
		List<SaveTransactionsDetailsOutReqDto> listSaveTransactionsDetailsOutReqDto = saveFullTransactionsOutReqDto.getListSaveTransactionsDetailsOutReqDto();
		List<SaveTransactionsDetailsOutResDto> detailsRes = new ArrayList<>();
		
		TransactionsOut transactionsOut = new TransactionsOut();
		try {
			begin();
			transactionsOut.setTransactionsOutCode(saveTransactionsOutReqDto.getTransactionsOutCode());
			transactionsOut.setCheckOutDate(LocalDate.now());
			transactionsOut.setExpiredDate(LocalDate.parse(saveTransactionsOutReqDto.getExpiredOutDate()));
			final TransactionsOut transactionsOutFinal=transactionsOutDao.saveOrUpdate(transactionsOut);
			listSaveTransactionsDetailsOutReqDto.forEach(i -> {
				SaveTransactionsDetailsOutResDto detail = new SaveTransactionsDetailsOutResDto();
				TransactionsDetailOut transactionsDetailOut = new TransactionsDetailOut();
				transactionsDetailOut.setTransactionsOut(transactionsOutFinal);
					if(i.getLocationsCode()!=null && i.getEmployeesCode()==null && i.getAssetsName()==null) {					
						Locations locations;
						try {
							locations = locationsDao.findByCode(i.getLocationsCode());
							transactionsDetailOut.setLocations(locations);
						} catch (Exception e) {
							e.printStackTrace();
							rollback();
						}
					}
					if(i.getLocationsCode()==null && i.getEmployeesCode()!=null && i.getAssetsName()==null) {					
						Employees employees;
						try {
							employees = employeesDao.findByCode(i.getEmployeesCode());
							transactionsDetailOut.setEmployees(employees);
						} catch (Exception e) {
							e.printStackTrace();
							rollback();
						}
					}
					if(i.getLocationsCode()==null && i.getEmployeesCode()==null && i.getAssetsName()!=null) {					
						Assets assets;
						try {
							assets = assetsDao.findByAssetsName(i.getAssetsName());
							transactionsDetailOut.setAssets(assets);
						} catch (Exception e) {
							e.printStackTrace();
							rollback();
						}
					}
				transactionsDetailOut.setTransactionDetailOutExpired(LocalDate.parse(i.getExpiredDate()));
				transactionsDetailOut.setCreatedBy(i.getCreatedBy());
				transactionsDetailOut.setIsActive(true);
				try {
					transactionsDetailOut = transactionsDetailOutDao.saveOrUpdate(transactionsDetailOut);
					detail.setId(transactionsDetailOut.getId());
					detailsRes.add(detail);
				} catch (Exception e) {
					e.printStackTrace();
					rollback();
				}
			});
			commit();
			headerRes.setId(transactionsOut.getId());
			detailsRes.forEach(i->{				
				headerRes.setListDetail(i);
			});
			saveFullTransactionsOutResDto.setSaveTransactionsOutResDto(headerRes);
			saveFullTransactionsOutResDto.setMessage("SUCCESS");
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return saveFullTransactionsOutResDto;
	}

	@Override
	public GetByTransactionsOutIdResDto findById(String id) throws Exception {
		GetByTransactionsOutIdResDto headerRes = new GetByTransactionsOutIdResDto();
		GetTransactionsOutDataDto header = new GetTransactionsOutDataDto();
		TransactionsOut headerDb = transactionsOutDao.findById(id);
		header.setTransactionsInCode(headerDb.getTransactionsOutCode());
		header.setCheckOutDate(headerDb.getCheckOutDate());
		header.setExpiredDate(headerDb.getExpiredDate());
		header.setVersion(headerDb.getVersion());
		header.setCreatedBy(headerDb.getCreatedBy());
		header.setCreatedAt(headerDb.getCreatedAt());
		header.setUpdatedBy(headerDb.getUpdatedBy());
		header.setUpdatedAt(headerDb.getUpdatedAt());
		headerRes.setGetTransactionsOutDataDto(header);
		headerRes.setMessage("SUCCESS");
		return headerRes;
	}

	@Override
	public GetByTransactionsOutCodeResDto findByCode(String code) throws Exception {
		GetByTransactionsOutCodeResDto headerRes = new GetByTransactionsOutCodeResDto();
		GetTransactionsOutDataDto header = new GetTransactionsOutDataDto();
		TransactionsOut headerDb = transactionsOutDao.findByCode(code);
		header.setTransactionsInCode(headerDb.getTransactionsOutCode());
		header.setCheckOutDate(headerDb.getCheckOutDate());
		header.setExpiredDate(headerDb.getExpiredDate());
		header.setVersion(headerDb.getVersion());
		header.setCreatedBy(headerDb.getCreatedBy());
		header.setCreatedAt(headerDb.getCreatedAt());
		header.setUpdatedBy(headerDb.getUpdatedBy());
		header.setUpdatedAt(headerDb.getUpdatedAt());
		headerRes.setData(header);
		headerRes.setMessage("SUCCESS");
		return headerRes;
	}

	@Override
	public GetAllTransactionsOutResDto findAll() throws Exception {
		GetAllTransactionsOutResDto headerRes = new GetAllTransactionsOutResDto();
		List<GetTransactionsOutDataDto> listHeader = new ArrayList<>();
		List<TransactionsOut> listHeaderDb = transactionsOutDao.findAll();
		listHeaderDb.forEach(i->{
			GetTransactionsOutDataDto header = new GetTransactionsOutDataDto();
			header.setTransactionsInCode(i.getTransactionsOutCode());
			header.setCheckOutDate(i.getCheckOutDate());
			header.setExpiredDate(i.getExpiredDate());
			header.setVersion(i.getVersion());
			header.setCreatedBy(i.getCreatedBy());
			header.setCreatedAt(i.getCreatedAt());
			header.setUpdatedBy(i.getUpdatedBy());
			header.setUpdatedAt(i.getUpdatedAt());
			listHeader.add(header);
		});
		headerRes.setGetTransactionsOutDataDto(listHeader);
		headerRes.setMessage("SUCCESS");
		return headerRes;
	}

}
