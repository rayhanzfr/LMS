package com.lawencon.lms.dto.transactionsoutexpired;

import java.time.LocalDate;

public class TransactionsOutExpiredResDto {
	
	private String transactionsOutCode;
	private String assetsName;
	private String locationsDeploy;
	private String employeesFullname;
	private LocalDate transactionsDetailOutExpired;
	public String getTransactionsOutCode() {
		return transactionsOutCode;
	}
	public void setTransactionsOutCode(String transactionsOutCode) {
		this.transactionsOutCode = transactionsOutCode;
	}
	public String getAssetsName() {
		return assetsName;
	}
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	public String getLocationsDeploy() {
		return locationsDeploy;
	}
	public void setLocationsDeploy(String locationsDeploy) {
		this.locationsDeploy = locationsDeploy;
	}
	public String getEmployeesFullname() {
		return employeesFullname;
	}
	public void setEmployeesFullname(String employeesFullname) {
		this.employeesFullname = employeesFullname;
	}
	public LocalDate getTransactionsDetailOutExpired() {
		return transactionsDetailOutExpired;
	}
	public void setTransactionsDetailOutExpired(LocalDate transactionsDetailOutExpired) {
		this.transactionsDetailOutExpired = transactionsDetailOutExpired;
	}
}
