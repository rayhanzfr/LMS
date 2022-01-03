package com.lawencon.lms.dto.transactionsin;

import java.util.List;

public class GetAllTransactionsInByUsersResDto {
	private List<GetTransactionsInDataDto> getTransactionsInDataDto;
	private String message;
	public List<GetTransactionsInDataDto> getGetTransactionsInDataDto() {
		return getTransactionsInDataDto;
	}
	public void setGetTransactionsInDataDto(List<GetTransactionsInDataDto> getTransactionsInDataDto) {
		this.getTransactionsInDataDto = getTransactionsInDataDto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
