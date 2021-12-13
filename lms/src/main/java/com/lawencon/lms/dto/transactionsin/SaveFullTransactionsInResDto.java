package com.lawencon.lms.dto.transactionsin;

public class SaveFullTransactionsInResDto {
	private SaveTransactionsInResDto saveTransactionsInResDto;
	private String message;

	public SaveTransactionsInResDto getSaveTransactionsInResDto() {
		return saveTransactionsInResDto;
	}

	public void setSaveTransactionsInResDto(SaveTransactionsInResDto saveTransactionsInResDto) {
		this.saveTransactionsInResDto = saveTransactionsInResDto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
