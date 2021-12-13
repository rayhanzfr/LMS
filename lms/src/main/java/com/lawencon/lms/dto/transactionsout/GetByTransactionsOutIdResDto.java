package com.lawencon.lms.dto.transactionsout;

public class GetByTransactionsOutIdResDto {
	private GetTransactionsOutDataDto getTransactionsOutDataDto;
	private String message;
	public GetTransactionsOutDataDto getGetTransactionsOutDataDto() {
		return getTransactionsOutDataDto;
	}
	public void setGetTransactionsOutDataDto(GetTransactionsOutDataDto getTransactionsOutDataDto) {
		this.getTransactionsOutDataDto = getTransactionsOutDataDto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
