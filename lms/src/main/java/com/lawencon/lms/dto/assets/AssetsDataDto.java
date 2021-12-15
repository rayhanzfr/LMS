package com.lawencon.lms.dto.assets;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AssetsDataDto {
	private String id;
	private String itemsId;
	private String itemsName;
	private String invoicesId;
	private String invoicesCode;
	private String assetsName;
	private String statusesAssetsId;
	private String statusesAssetsName;
	private String statusesInOutId;
	private String statusesInOutName;
	private LocalDate assetsExpired;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
	private boolean isActive;
	private Integer version;

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

	public String getStatusesAssetsId() {
		return statusesAssetsId;
	}

	public void setStatusesAssetsId(String statusesAssetsId) {
		this.statusesAssetsId = statusesAssetsId;
	}

	public String getStatusesInOutId() {
		return statusesInOutId;
	}

	public void setStatusesInOutId(String statusesInOutId) {
		this.statusesInOutId = statusesInOutId;
	}
	
}
