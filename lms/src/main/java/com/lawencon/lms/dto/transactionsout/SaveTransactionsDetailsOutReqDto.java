package com.lawencon.lms.dto.transactionsout;

public class SaveTransactionsDetailsOutReqDto {
	private String transactionsInId;
	private String locationsCode;
	private String employeesCode;
	private String assetsName;
	private String expiredDate;
	private String createdBy;
	private String isActive;

	public String getTransactionsInId() {
		return transactionsInId;
	}

	public void setTransactionsInId(String transactionsInId) {
		this.transactionsInId = transactionsInId;
	}

	public String getLocationsCode() {
		return locationsCode;
	}

	public void setLocationsCode(String locationsCode) {
		this.locationsCode = locationsCode;
	}

	public String getEmployeesCode() {
		return employeesCode;
	}

	public void setEmployeesCode(String employeesCode) {
		this.employeesCode = employeesCode;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
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
