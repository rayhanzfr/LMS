package com.lawencon.lms.dto.transactionsdetailin;

public class InsertResTransactionsDetailInDto {
	private InsertResTransactionsDetailInDataDto data;
	private String msg;

	public InsertResTransactionsDetailInDataDto getData() {
		return data;
	}

	public void setData(InsertResTransactionsDetailInDataDto data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
