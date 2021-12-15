package com.lawencon.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.TransactionsDetailInDao;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsDetailInCodeResDto;
import com.lawencon.lms.dto.transactionsin.GetTransactionsDetailsInDataDto;
import com.lawencon.lms.model.TransactionsDetailIn;
import com.lawencon.lms.service.TransactionsDetailInService;

@Service
public class TransactionsDetailInServiceImpl extends BaseServiceImpl implements TransactionsDetailInService {

	@Autowired
	private TransactionsDetailInDao transactionsDetailInDao;

	@Override
	public GetByTransactionsDetailInCodeResDto findByTransactionInCode(String code) throws Exception {
		GetByTransactionsDetailInCodeResDto findByCodeTransactionInDto = new GetByTransactionsDetailInCodeResDto();
		List<GetTransactionsDetailsInDataDto> listTdinDataDto = new ArrayList<>();
		List<TransactionsDetailIn> listTdin = transactionsDetailInDao.findByTransactionInCode(code);
		
		listTdin.forEach(listDetail ->{
			GetTransactionsDetailsInDataDto tdinDataDto = new GetTransactionsDetailsInDataDto();
			tdinDataDto.setId(listDetail.getId());
			tdinDataDto.setTransactionsInCode(listDetail.getTransactionsIn().getTransactionsInCode());
			tdinDataDto.setVersion(listDetail.getVersion());
			tdinDataDto.setCreatedAt(listDetail.getCreatedAt());
			tdinDataDto.setCreatedBy(listDetail.getCreatedBy());
			tdinDataDto.setUpdatedAt(listDetail.getUpdatedAt());
			tdinDataDto.setUpdatedBy(listDetail.getUpdatedBy());
			tdinDataDto.setIsActive(listDetail.getIsActive());
			
			listTdinDataDto.add(tdinDataDto);
		});
		
		

		findByCodeTransactionInDto.setGetTransactionsDetailsInDataDto(listTdinDataDto);
		findByCodeTransactionInDto.setMessage("Sukses");

		return findByCodeTransactionInDto;
	}
}
