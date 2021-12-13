package com.lawencon.lms.dto.transactionsin;

import java.util.List;

public class GetAllTransactionsInDto {
	private List<TransactionsInDataDto> data;
	private String msg;

	public List<TransactionsInDataDto> getData() {
		return data;
	}

	public void setData(List<TransactionsInDataDto> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
