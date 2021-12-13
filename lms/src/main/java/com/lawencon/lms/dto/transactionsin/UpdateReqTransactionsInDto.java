package com.lawencon.lms.dto.transactionsin;

public class UpdateReqTransactionsInDto {

	private String id;
	private Integer version;
	private String transactionsCode;
	private String transactionsDate;
	private String transactionsOutId;
	private String updatedBy;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
