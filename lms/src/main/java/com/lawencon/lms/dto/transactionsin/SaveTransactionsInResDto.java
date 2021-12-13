package com.lawencon.lms.dto.transactionsin;

public class SaveTransactionsInResDto {
	private SaveTransactionsInDataResDto data;
	private String msg;

	public SaveTransactionsInDataResDto getData() {
		return data;
	}

	public void setData(SaveTransactionsInDataResDto data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
