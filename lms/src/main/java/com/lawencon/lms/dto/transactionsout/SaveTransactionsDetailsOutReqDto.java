package com.lawencon.lms.dto.transactionsout;

public class SaveTransactionsDetailsOutReqDto {
	private String transactionsOutId;
	private String locationsCode;
	private String employeesCode;
	private String assetsName;
	private String expiredDate;

	public String getTransactionsOutId() {
		return transactionsOutId;
	}

	public void setTransactionsOutId(String transactionsOutId) {
		this.transactionsOutId = transactionsOutId;
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

	
	
	
}
