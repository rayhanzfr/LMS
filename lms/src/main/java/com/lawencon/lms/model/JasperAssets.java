package com.lawencon.lms.model;

import java.time.LocalDate;

public class JasperAssets {

	private String assetsName;
	private String itemsName;
	private String itemsTypesName;
	private String companiesName;
	private String itemsBrandsName;
	private String statusesAssetsName;
	private String statusesInOutName;
	private String assetsExpired;
	
	
	
	public String getCompaniesName() {
		return companiesName;
	}
	public void setCompaniesName(String companiesName) {
		this.companiesName = companiesName;
	}
	public String getAssetsExpired() {
		return assetsExpired;
	}
	public void setAssetsExpired(String assetsExpired) {
		this.assetsExpired = assetsExpired;
	}
	public String getAssetsName() {
		return assetsName;
	}
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	public String getItemsTypesName() {
		return itemsTypesName;
	}
	public void setItemsTypesName(String itemsTypesName) {
		this.itemsTypesName = itemsTypesName;
	}
	public String getItemsBrandsName() {
		return itemsBrandsName;
	}
	public void setItemsBrandsName(String itemsBrandsName) {
		this.itemsBrandsName = itemsBrandsName;
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
}