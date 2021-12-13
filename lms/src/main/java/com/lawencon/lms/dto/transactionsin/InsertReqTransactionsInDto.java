package com.lawencon.lms.dto.transactionsin;

public class InsertReqTransactionsInDto {

	private String transactionsCode;
	private String transactionsDate;
	private String transactionsOutId;
	private String createdBy;

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
