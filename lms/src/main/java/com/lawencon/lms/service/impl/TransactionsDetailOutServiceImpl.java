package com.lawencon.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.TransactionsDetailOutDao;
import com.lawencon.lms.dto.transactionsout.GetAllTransactionsDetailsOutResDto;
import com.lawencon.lms.dto.transactionsout.GetTransactionsDetailsOutDataDto;
import com.lawencon.lms.dto.transactionsoutexpired.TransactionsOutExpired;
import com.lawencon.lms.model.TransactionsDetailOut;
import com.lawencon.lms.service.TransactionsDetailOutService;

public class TransactionsDetailOutServiceImpl extends BaseServiceLmsImpl implements TransactionsDetailOutService {

	@Autowired
	private TransactionsDetailOutDao transactionsDetailOutDao;
	
	@Override
	public GetAllTransactionsDetailsOutResDto findByTransactionOutCode(String code) throws Exception {
		GetAllTransactionsDetailsOutResDto resDto = new GetAllTransactionsDetailsOutResDto();
		List<GetTransactionsDetailsOutDataDto> listOutDataDto = new ArrayList<GetTransactionsDetailsOutDataDto>();
		List<TransactionsDetailOut> listOut = transactionsDetailOutDao.findByTransactionOutCode(code);
		
		listOut.forEach(detail->{
			GetTransactionsDetailsOutDataDto data = new GetTransactionsDetailsOutDataDto();
			data.setLocationsId(detail.getLocations().getId());
			data.setLocationsCode(detail.getLocations().getLocationsCode());
			data.setEmployeesId(detail.getEmployees().getId());
			data.setEmployeesCode(detail.getEmployees().getEmployeesCode());
			data.setAssetsId(detail.getAssets().getId());
			data.setAssetsCode(detail.getAssets().getAssetsName());
			data.setVersion(detail.getVersion());
			data.setCreatedBy(detail.getCreatedBy());
			data.setCreatedAt(detail.getCreatedAt());
			data.setUpdatedBy(detail.getUpdatedBy());
			data.setUpdatedAt(detail.getUpdatedAt());
			
			listOutDataDto.add(data);
		});
		
		resDto.setGetTransactionsDetailsOutDataDto(listOutDataDto);
		resDto.setMessage("SUCCESS");
		return resDto;
	}

	@Override
	public List<TransactionsOutExpired> getMoreThanExpired() throws Exception {
		List<TransactionsDetailOut> transactionsDetail = transactionsDetailOutDao.findMoreThanExpiredDate();
		List<TransactionsOutExpired> transactionsExpired = new ArrayList<TransactionsOutExpired>(); 
		transactionsDetail.forEach(data->{
			TransactionsOutExpired toe = new TransactionsOutExpired();
			toe.setAssetsName(data.getAssets().getAssetsName());
			toe.setEmployeesFullname(data.getEmployees().getEmployeesFullname());
			toe.setLocationsDeploy(data.getLocations().getLocationsDeploy());
			toe.setTransactionsOutCode(data.getTransactionsOut().getTransactionsOutCode());
			toe.setTransactionsDetailOutExpired(data.getTransactionDetailOutExpired());
			transactionsExpired.add(toe);
		});
		return transactionsExpired;
	}

	@Override
	public List<TransactionsOutExpired> findAlmostExpired() throws Exception {
		List<TransactionsDetailOut> detail = transactionsDetailOutDao.findAlmostExpired();
		List<TransactionsOutExpired> transactionsExpired = new ArrayList<TransactionsOutExpired>(); 
		detail.forEach(data->{
			TransactionsOutExpired toe = new TransactionsOutExpired();
			toe.setAssetsName(data.getAssets().getAssetsName());
			toe.setEmployeesFullname(data.getEmployees().getEmployeesFullname());
			toe.setLocationsDeploy(data.getLocations().getLocationsDeploy());
			toe.setTransactionsOutCode(data.getTransactionsOut().getTransactionsOutCode());
			toe.setTransactionsDetailOutExpired(data.getTransactionDetailOutExpired());
			transactionsExpired.add(toe);
		});
		return transactionsExpired;
	}
}
