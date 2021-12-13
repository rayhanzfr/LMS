package com.lawencon.lms.dto.transactionsin;

public class GetByTransactionsInCodeResDto {
	private GetTransactionsInDataDto data;
	private String message;

	public GetTransactionsInDataDto getData() {
		return data;
	}

	public void setData(GetTransactionsInDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
