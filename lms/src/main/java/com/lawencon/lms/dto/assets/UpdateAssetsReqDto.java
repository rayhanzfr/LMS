package com.lawencon.lms.dto.assets;

import java.time.LocalDate;

public class UpdateAssetsReqDto {
	
	private String itemsCode;
	private String invoicesCode;
	private String statusesAssetsCode;
	private String statusesInOutCode;
	private String assetsExpired;
	
	
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
	
	
}
