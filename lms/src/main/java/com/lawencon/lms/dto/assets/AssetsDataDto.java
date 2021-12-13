package com.lawencon.lms.dto.assets;

import java.time.LocalDate;

public class AssetsDataDto {
	private String itemsId;
	private String itemsName;
	private String invoicesId;
	private String invoicesCode;
	private String assetsName;
	private String statusesAssetsName;
	private String statusesInOutName;
	private LocalDate assetsExpired;

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getStatusesAssetsName() {
		return statusesAssetsName;
	}

	public void setStatusesAssetsName(String statusesAssetsName) {
		this.statusesAssetsName = statusesAssetsName;
	}

	public String getStatusesInOutName() {
		return statusesInOutName;
	}

	public void setStatusesInOutName(String statusesInOutName) {
		this.statusesInOutName = statusesInOutName;
	}

	public LocalDate getAssetsExpired() {
		return assetsExpired;
	}

	public void setAssetsExpired(LocalDate assetsExpired) {
		this.assetsExpired = assetsExpired;
	}

	public String getItemsId() {
		return itemsId;
	}

	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}

	public String getInvoicesId() {
		return invoicesId;
	}

	public void setInvoicesId(String invoicesId) {
		this.invoicesId = invoicesId;
	}

	public String getInvoicesCode() {
		return invoicesCode;
	}

	public void setInvoicesCode(String invoicesCode) {
		this.invoicesCode = invoicesCode;
	}
}
