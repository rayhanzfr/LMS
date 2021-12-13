package com.lawencon.lms.dto.transactionsin;

public class SaveTransactionsInReqDto {
	private String transactionsCode;
	private String transactionsDate;
	private String transactionsOutId;

	public String getTransactionsCode() {
		return transactionsCode;
	}

	public void setTransactionsCode(String transactionsCode) {
		this.transactionsCode = transactionsCode;
	}

	public String getTransactionsDate() {
		return transactionsDate;
	}

	public void setTransactionsDate(String transactionsDate) {
		this.transactionsDate = transactionsDate;
	}

	public String getTransactionsOutId() {
		return transactionsOutId;
	}

	public void setTransactionsOutId(String transactionsOutId) {
		this.transactionsOutId = transactionsOutId;
	}

}
