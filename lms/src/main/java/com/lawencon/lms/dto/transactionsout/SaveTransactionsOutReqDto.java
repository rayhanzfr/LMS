package com.lawencon.lms.dto.transactionsout;

public class SaveTransactionsOutReqDto {
	private String transactionsOutCode;
	private String checkOutDate;
	private String expiredOutDate;
	private String createdBy;
	private String isActive;
	
	public String getTransactionsOutCode() {
		return transactionsOutCode;
	}
	public void setTransactionsOutCode(String transactionsOutCode) {
		this.transactionsOutCode = transactionsOutCode;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getExpiredOutDate() {
		return expiredOutDate;
	}
	public void setExpiredOutDate(String expiredOutDate) {
		this.expiredOutDate = expiredOutDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
}
