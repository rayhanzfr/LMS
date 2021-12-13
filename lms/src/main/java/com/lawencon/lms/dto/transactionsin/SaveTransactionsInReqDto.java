package com.lawencon.lms.dto.transactionsin;

import java.util.List;

import com.lawencon.lms.dto.transactionsdetailin.SaveTransactionsDetailInReqDto;

public class SaveTransactionsInReqDto {

	private List<SaveTransactionsDetailInReqDto> transactionsDetailDto;
	private String transactionsOutId;
	
	public List<SaveTransactionsDetailInReqDto> getTransactionsDetailDto() {
		return transactionsDetailDto;
	}

	public void setTransactionsDetailDto(List<SaveTransactionsDetailInReqDto> transactionsDetailDto) {
		this.transactionsDetailDto = transactionsDetailDto;
	}

	public String getTransactionsOutId() {
		return transactionsOutId;
	}

	public void setTransactionsOutId(String transactionsOutId) {
		this.transactionsOutId = transactionsOutId;
	}

}
