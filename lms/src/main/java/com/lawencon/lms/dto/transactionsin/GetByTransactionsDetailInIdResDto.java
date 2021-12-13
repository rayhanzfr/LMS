package com.lawencon.lms.dto.transactionsin;

public class GetByTransactionsDetailInIdResDto {
	private GetTransactionsInDataDto getTransactionsInDataDto;
	private String message;

	public GetTransactionsInDataDto getGetTransactionsInDataDto() {
		return getTransactionsInDataDto;
	}

	public void setGetTransactionsInDataDto(GetTransactionsInDataDto getTransactionsInDataDto) {
		this.getTransactionsInDataDto = getTransactionsInDataDto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
