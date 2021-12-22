package com.lawencon.lms.dto.transactionsin;

public class SaveTransactionsDetailsInReqDto {

	private String transactionsInId;
	private String locationsCode;
	private String employeesCode;
	private String assetsName;
	private String statusesTransactionsCode;
	private String returnDate;

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

	public String getStatusesTransactionsCode() {
		return statusesTransactionsCode;
	}

	public void setStatusesTransactionsCode(String statusesTransactionsCode) {
		this.statusesTransactionsCode = statusesTransactionsCode;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

}
