package com.lawencon.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.TransactionsDetailOutDao;
import com.lawencon.lms.dto.transactionsout.GetAllTransactionsDetailsOutResDto;
import com.lawencon.lms.dto.transactionsout.GetTransactionsDetailsOutDataDto;
import com.lawencon.lms.model.TransactionsDetailOut;
import com.lawencon.lms.service.TransactionsDetailOutService;

public class TransactionsDetailOutServiceImpl extends BaseServiceImpl implements TransactionsDetailOutService {

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

}
