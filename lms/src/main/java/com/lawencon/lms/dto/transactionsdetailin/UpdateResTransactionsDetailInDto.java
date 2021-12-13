package com.lawencon.lms.dto.transactionsdetailin;

public class UpdateResTransactionsDetailInDto {
	private UpdateResTransactionsDetailInDataDto data;
	private String msg;

	public UpdateResTransactionsDetailInDataDto getData() {
		return data;
	}

	public void setData(UpdateResTransactionsDetailInDataDto data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
