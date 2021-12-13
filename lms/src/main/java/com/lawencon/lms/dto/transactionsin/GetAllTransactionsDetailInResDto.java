package com.lawencon.lms.dto.transactionsin;

import java.util.List;

public class GetAllTransactionsDetailInResDto {
	private List<GetTransactionsDetailsInDataDto> getTransactionsDetailsInDataDto;
	private String message;

	public List<GetTransactionsDetailsInDataDto> getGetTransactionsDetailsInDataDto() {
		return getTransactionsDetailsInDataDto;
	}

	public void setGetTransactionsDetailsInDataDto(
			List<GetTransactionsDetailsInDataDto> getTransactionsDetailsInDataDto) {
		this.getTransactionsDetailsInDataDto = getTransactionsDetailsInDataDto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
