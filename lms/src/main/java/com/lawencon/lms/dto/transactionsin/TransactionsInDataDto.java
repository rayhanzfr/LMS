package com.lawencon.lms.dto.transactionsin;

import java.time.LocalDateTime;
import java.util.List;

import com.lawencon.lms.dto.transactionsdetailin.SaveTransactionsDetailInReqDto;

public class TransactionsInDataDto {
	private String id;
	private String transactionsCode;
	private String transactionsDate;
	private String transactionsOutId;
	private List<SaveTransactionsDetailInReqDto> transactionsDetailIn;
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

	public String getTransactionsCode() {
		return transactionsCode;
	}

	public void setTransactionsCode(String transactionsCode) {
		this.transactionsCode = transactionsCode;
	}

	public String getTransactionsDate() {
		return transactionsDate;
	}

	public void setTransactionsDate(String transactionsDate) {
		this.transactionsDate = transactionsDate;
	}

	public String getTransactionsOutId() {
		return transactionsOutId;
	}

	public void setTransactionsOutId(String transactionsOutId) {
		this.transactionsOutId = transactionsOutId;
	}
	
	public List<SaveTransactionsDetailInReqDto> getTransactionsDetailIn() {
		return transactionsDetailIn;
	}

	public void setTransactionsDetailIn(List<SaveTransactionsDetailInReqDto> transactionsDetailIn) {
		this.transactionsDetailIn = transactionsDetailIn;
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
