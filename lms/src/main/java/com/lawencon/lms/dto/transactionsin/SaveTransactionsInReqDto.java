package com.lawencon.lms.dto.transactionsin;

public class SaveTransactionsInReqDto {
	private String transactionsInCode;
	private String transactionsInDate;
	private String transactionsOutCode;

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

	public String getTransactionsOutCode() {
		return transactionsOutCode;
	}

	public void setTransactionsOutCode(String transactionsOutCode) {
		this.transactionsOutCode = transactionsOutCode;
	}

}
