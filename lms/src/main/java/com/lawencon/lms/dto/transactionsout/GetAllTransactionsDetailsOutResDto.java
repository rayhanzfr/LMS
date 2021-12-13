package com.lawencon.lms.dto.transactionsout;

import java.util.List;

public class GetAllTransactionsDetailsOutResDto {
	private List<GetTransactionsDetailsOutDataDto> getTransactionsDetailsOutDataDto;
	private String message;
	
	public List<GetTransactionsDetailsOutDataDto> getGetTransactionsDetailsOutDataDto() {
		return getTransactionsDetailsOutDataDto;
	}
	public void setGetTransactionsDetailsOutDataDto(
			List<GetTransactionsDetailsOutDataDto> getTransactionsDetailsOutDataDto) {
		this.getTransactionsDetailsOutDataDto = getTransactionsDetailsOutDataDto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
