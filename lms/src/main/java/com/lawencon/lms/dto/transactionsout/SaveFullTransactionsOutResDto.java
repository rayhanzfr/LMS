package com.lawencon.lms.dto.transactionsout;

public class SaveFullTransactionsOutResDto {
	private SaveTransactionsOutResDto saveTransactionsOutResDto;
	private String message;
	
	public SaveTransactionsOutResDto getSaveTransactionsOutResDto() {
		return saveTransactionsOutResDto;
	}
	public void setSaveTransactionsOutResDto(SaveTransactionsOutResDto saveTransactionsOutResDto) {
		this.saveTransactionsOutResDto = saveTransactionsOutResDto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
