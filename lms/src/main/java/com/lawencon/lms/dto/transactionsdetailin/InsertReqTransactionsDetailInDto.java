package com.lawencon.lms.dto.transactionsdetailin;

public class InsertReqTransactionsDetailInDto {

	private String transactionsInId;
	private String locationsId;
	private String employeesId;
	private String assetsId;
	private String statusesTransactionsId;
	private String returnDate;
	private String createdBy;

	public String getTransactionsInId() {
		return transactionsInId;
	}

	public void setTransactionsInId(String transactionsInId) {
		this.transactionsInId = transactionsInId;
	}

	public String getLocationsId() {
		return locationsId;
	}

	public void setLocationsId(String locationsId) {
		this.locationsId = locationsId;
	}

	public String getEmployeesId() {
		return employeesId;
	}

	public void setEmployeesId(String employeesId) {
		this.employeesId = employeesId;
	}

	public String getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(String assetsId) {
		this.assetsId = assetsId;
	}

	public String getStatusesTransactionsId() {
		return statusesTransactionsId;
	}

	public void setStatusesTransactionsId(String statusesTransactionsId) {
		this.statusesTransactionsId = statusesTransactionsId;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
