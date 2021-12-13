package com.lawencon.lms.dto.transactionsin;

public class InsertResTransactionsInDto {
	private InsertResTransactionsInDataDto data;
	private String msg;

	public InsertResTransactionsInDataDto getData() {
		return data;
	}

	public void setData(InsertResTransactionsInDataDto data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
