package com.lawencon.lms.dto.transactionsdetailin;

import java.util.List;

public class GetAllTransactionsDetailInDto {
	private List<TransactionsDetailInDataDto> data;
	private String msg;

	public List<TransactionsDetailInDataDto> getData() {
		return data;
	}

	public void setData(List<TransactionsDetailInDataDto> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
