package com.lawencon.lms.dto.transactionsout;

import java.util.List;

public class GetAllTransactionsOutResDto {
	private List<GetTransactionsOutDataDto> getTransactionsOutDataDto;
	private String message;
	public List<GetTransactionsOutDataDto> getGetTransactionsOutDataDto() {
		return getTransactionsOutDataDto;
	}
	public void setGetTransactionsOutDataDto(List<GetTransactionsOutDataDto> getTransactionsOutDataDto) {
		this.getTransactionsOutDataDto = getTransactionsOutDataDto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
