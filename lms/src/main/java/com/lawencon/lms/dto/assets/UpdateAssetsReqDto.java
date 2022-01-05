package com.lawencon.lms.dto.assets;

public class UpdateAssetsReqDto {
	
	private String id;
	private String itemsCode;
	private String invoicesCode;
	private String companiesCode;
	private String assetsName;
	private String statusesAssetsCode;
	private String statusesInOutCode;
	private String assetsExpired;
	private String updatedBy;
	private boolean isActive;
	private Integer version;
	
	
	public String getCompaniesCode() {
		return companiesCode;
	}
	public void setCompaniesCode(String companiesCode) {
		this.companiesCode = companiesCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemsCode() {
		return itemsCode;
	}
	public void setItemsCode(String itemsCode) {
		this.itemsCode = itemsCode;
	}
	public String getInvoicesCode() {
		return invoicesCode;
	}
	public void setInvoicesCode(String invoicesCode) {
		this.invoicesCode = invoicesCode;
	}
	public String getAssetsName() {
		return assetsName;
	}
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	public String getStatusesAssetsCode() {
		return statusesAssetsCode;
	}
	public void setStatusesAssetsCode(String statusesAssetsCode) {
		this.statusesAssetsCode = statusesAssetsCode;
	}
	public String getStatusesInOutCode() {
		return statusesInOutCode;
	}
	public void setStatusesInOutCode(String statusesInOutCode) {
		this.statusesInOutCode = statusesInOutCode;
	}
	public String getAssetsExpired() {
		return assetsExpired;
	}
	public void setAssetsExpired(String assetsExpired) {
		this.assetsExpired = assetsExpired;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}
