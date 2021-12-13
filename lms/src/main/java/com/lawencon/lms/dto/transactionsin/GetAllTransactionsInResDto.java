package com.lawencon.lms.dto.transactionsin;

import java.util.List;

public class GetAllTransactionsInResDto {
	private List<GetTransactionsInDataDto> GetTransactionsInDataDto;
	private String message;

	public List<GetTransactionsInDataDto> getGetTransactionsInDataDto() {
		return GetTransactionsInDataDto;
	}

	public void setGetTransactionsInDataDto(List<GetTransactionsInDataDto> getTransactionsInDataDto) {
		GetTransactionsInDataDto = getTransactionsInDataDto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
