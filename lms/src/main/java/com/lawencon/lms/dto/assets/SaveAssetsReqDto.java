package com.lawencon.lms.dto.assets;


public class SaveAssetsReqDto {
	private String itemsCode;
	private String invoicesCode;
	private String assetsName;
	private String statusesAssetsCode;
	private String statusesInOutCode;
	private String assetsExpired;
	private String createdBy;
	private boolean isActive;

	public String getItemsName() {
		return itemsCode;
	}

	public void setItemsName(String itemsCode) {
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

	public String getItemsCode() {
		return itemsCode;
	}

	public void setItemsCode(String itemsCode) {
		this.itemsCode = itemsCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
