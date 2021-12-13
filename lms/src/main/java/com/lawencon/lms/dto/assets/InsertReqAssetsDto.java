package com.lawencon.lms.dto.assets;

import java.time.LocalDate;

public class InsertReqAssetsDto {
	private String itemsCode;
	private String invoicesCode;
	private String assetsName;
	private String statusesAssetsCode;
	private String statusesInOutCode;
	private String assetsExpired;

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

	public LocalDate getAssetsExpired() {
		return assetsExpired;
	}

	public void setAssetsExpired(LocalDate assetsExpired) {
		this.assetsExpired = assetsExpired;
	}
}
