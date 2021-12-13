package com.lawencon.lms.dto.transactionsout;

public class GetByTransactionsDetailsOutIdResDto {
	private GetTransactionsDetailsOutDataDto getTransactionsDetailsOutDataDto;
	private String message;
	
	public GetTransactionsDetailsOutDataDto getGetTransactionsDetailsOutDataDto() {
		return getTransactionsDetailsOutDataDto;
	}
	public void setGetTransactionsDetailsOutDataDto(GetTransactionsDetailsOutDataDto getTransactionsDetailsOutDataDto) {
		this.getTransactionsDetailsOutDataDto = getTransactionsDetailsOutDataDto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
