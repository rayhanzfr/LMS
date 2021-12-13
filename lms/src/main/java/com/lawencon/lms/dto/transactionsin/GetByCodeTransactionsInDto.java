package com.lawencon.lms.dto.transactionsin;

public class GetByCodeTransactionsInDto {
	private TransactionsInDataDto data;
	private String msg;

	public TransactionsInDataDto getData() {
		return data;
	}

	public void setData(TransactionsInDataDto data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
