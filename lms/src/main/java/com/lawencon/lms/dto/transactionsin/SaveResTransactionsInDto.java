package com.lawencon.lms.dto.transactionsin;

public class SaveResTransactionsInDto {
	private SaveResTransactionsInDataDto data;
	private String msg;

	public SaveResTransactionsInDataDto getData() {
		return data;
	}

	public void setData(SaveResTransactionsInDataDto data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
