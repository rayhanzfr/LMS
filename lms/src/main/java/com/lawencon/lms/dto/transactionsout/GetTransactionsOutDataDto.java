package com.lawencon.lms.dto.transactionsout;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GetTransactionsOutDataDto {
	private String transactionsInCode;
	private LocalDate checkOutDate;
	private LocalDate expiredDate;
	private Integer version;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;

	public String getTransactionsInCode() {
		return transactionsInCode;
	}

	public void setTransactionsInCode(String transactionsInCode) {
		this.transactionsInCode = transactionsInCode;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
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
