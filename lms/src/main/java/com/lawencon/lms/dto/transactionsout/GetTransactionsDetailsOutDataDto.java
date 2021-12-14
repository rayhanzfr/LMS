package com.lawencon.lms.dto.transactionsout;

import java.time.LocalDateTime;

public class GetTransactionsDetailsOutDataDto {
	private String locationsId;
	private String locationsCode;
	private String employeesId;
	private String employeesCode;
	private String assetsId;
	private String assetsCode;
	private Integer version;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
	public String getLocationsId() {
		return locationsId;
	}
	public void setLocationsId(String locationsId) {
		this.locationsId = locationsId;
	}
	public String getLocationsCode() {
		return locationsCode;
	}
	public void setLocationsCode(String locationsCode) {
		this.locationsCode = locationsCode;
	}
	public String getEmployeesId() {
		return employeesId;
	}
	public void setEmployeesId(String employeesId) {
		this.employeesId = employeesId;
	}
	public String getEmployeesCode() {
		return employeesCode;
	}
	public void setEmployeesCode(String employeesCode) {
		this.employeesCode = employeesCode;
	}
	public String getAssetsId() {
		return assetsId;
	}
	public void setAssetsId(String assetsId) {
		this.assetsId = assetsId;
	}
	public String getAssetsCode() {
		return assetsCode;
	}
	public void setAssetsCode(String assetsCode) {
		this.assetsCode = assetsCode;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}