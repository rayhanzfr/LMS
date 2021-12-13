package com.lawencon.lms.dto.transactionsin;

import java.util.List;

public class SaveFullTransactionsInReqDto {

	private List<SaveTransactionsDetailsInReqDto> transactionsDetailDto;
	private SaveTransactionsInReqDto saveTransactionsInReqDto;

	public List<SaveTransactionsDetailsInReqDto> getTransactionsDetailDto() {
		return transactionsDetailDto;
	}

	public void setTransactionsDetailDto(List<SaveTransactionsDetailsInReqDto> transactionsDetailDto) {
		this.transactionsDetailDto = transactionsDetailDto;
	}

	public SaveTransactionsInReqDto getSaveTransactionsInReqDto() {
		return saveTransactionsInReqDto;
	}

	public void setSaveTransactionsInReqDto(SaveTransactionsInReqDto saveTransactionsInReqDto) {
		this.saveTransactionsInReqDto = saveTransactionsInReqDto;
	}

}
