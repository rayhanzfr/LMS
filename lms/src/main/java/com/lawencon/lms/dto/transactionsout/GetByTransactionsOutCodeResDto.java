package com.lawencon.lms.dto.transactionsout;

import com.lawencon.lms.dto.transactionsout.GetTransactionsOutDataDto;

public class GetByTransactionsOutCodeResDto {
	private GetTransactionsOutDataDto data;
	private String message;

	public GetTransactionsOutDataDto getData() {
		return data;
	}

	public void setData(GetTransactionsOutDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
