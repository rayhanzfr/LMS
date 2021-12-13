package com.lawencon.lms.dto.transactionsdetailin;

public class GetByIdTransactionsDetailInDto {
	private TransactionsDetailInDataDto data;
	private String msg;

	public TransactionsDetailInDataDto getData() {
		return data;
	}

	public void setData(TransactionsDetailInDataDto data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
