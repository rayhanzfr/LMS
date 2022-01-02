package com.lawencon.lms.dto.assets;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.lawencon.lms.model.Files;

public class AssetsDataDto {
	private String id;
	private String itemsName;
	private String itemsCode;
	private String invoicesCode;
	private String assetsName;
	private Files files;
	private String statusesAssetsName;
	private String statusesAssetsCode;
	private String statusesInOutName;
	private String statusesInOutCode;
	private LocalDate assetsExpired;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
	private boolean isActive;
	private Integer version;

	
	
	public String getItemsCode() {
		return itemsCode;
	}

	public void setItemsCode(String itemsCode) {
		this.itemsCode = itemsCode;
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

	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}

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

	public String getInvoicesCode() {
		return invoicesCode;
	}

	public void setInvoicesCode(String invoicesCode) {
		this.invoicesCode = invoicesCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean isActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}