package com.lawencon.lms.dto.transactionsin;

import java.time.LocalDateTime;

public class GetTransactionsDetailsInDataDto {
	private String id;
	private String transactionsInId;
	private String locationsId;
	private String locationsDeploy;
	private String employeesId;
	private String employeesFullname;
	private String assetsId;
	private String assetsName;
	private String statusesTransactionsId;
	private String statusesTransactionsName;
	private String returnDate;
	private Integer version;
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime updatedAt;
	private String updatedBy;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getLocationsDeploy() {
		return locationsDeploy;
	}

	public void setLocationsDeploy(String locationsDeploy) {
		this.locationsDeploy = locationsDeploy;
	}

	public String getEmployeesId() {
		return employeesId;
	}

	public void setEmployeesId(String employeesId) {
		this.employeesId = employeesId;
	}

	public String getEmployeesFullname() {
		return employeesFullname;
	}

	public void setEmployeesFullname(String employeesFullname) {
		this.employeesFullname = employeesFullname;
	}

	public String getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(String assetsId) {
		this.assetsId = assetsId;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getStatusesTransactionsId() {
		return statusesTransactionsId;
	}

	public void setStatusesTransactionsId(String statusesTransactionsId) {
		this.statusesTransactionsId = statusesTransactionsId;
	}

	public String getStatusesTransactionsName() {
		return statusesTransactionsName;
	}

	public void setStatusesTransactionsName(String statusesTransactionsName) {
		this.statusesTransactionsName = statusesTransactionsName;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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
