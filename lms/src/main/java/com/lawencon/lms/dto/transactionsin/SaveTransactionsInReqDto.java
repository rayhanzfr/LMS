package com.lawencon.lms.dto.transactionsin;

public class SaveTransactionsInReqDto {
	private String transactionsInCode;
	private String transactionsInDate;
	private String transactionsOutId;

	public String getTransactionsInCode() {
		return transactionsInCode;
	}

	public void setTransactionsInCode(String transactionsInCode) {
		this.transactionsInCode = transactionsInCode;
	}

	public String getTransactionsInDate() {
		return transactionsInDate;
	}

	public void setTransactionsInDate(String transactionsInDate) {
		this.transactionsInDate = transactionsInDate;
	}

	public String getTransactionsOutId() {
		return transactionsOutId;
	}

	public void setTransactionsOutId(String transactionsOutId) {
		this.transactionsOutId = transactionsOutId;
	}

}
