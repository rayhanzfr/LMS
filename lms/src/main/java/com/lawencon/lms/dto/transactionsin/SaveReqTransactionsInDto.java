package com.lawencon.lms.dto.transactionsin;

import java.util.List;

import com.lawencon.lms.dto.transactionsdetailin.InsertReqTransactionsDetailInDto;

public class SaveReqTransactionsInDto {

	private List<InsertReqTransactionsDetailInDto> transactionsDetailDto;
	private String transactionsOutId;
	
	public List<InsertReqTransactionsDetailInDto> getTransactionsDetailDto() {
		return transactionsDetailDto;
	}

	public void setTransactionsDetailDto(List<InsertReqTransactionsDetailInDto> transactionsDetailDto) {
		this.transactionsDetailDto = transactionsDetailDto;
	}

	public String getTransactionsOutId() {
		return transactionsOutId;
	}

	public void setTransactionsOutId(String transactionsOutId) {
		this.transactionsOutId = transactionsOutId;
	}

}
