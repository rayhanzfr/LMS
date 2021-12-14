package com.lawencon.lms.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class TransactionsOut extends BaseEntity {

	@Column(nullable = false)
	private String transactionsOutCode;
	@Column(nullable = false)
	private LocalDate checkOutDate;
	@Column
	private LocalDate expiredDate;
	
	public String getTransactionsOutCode() {
		return transactionsOutCode;
	}
	public void setTransactionsOutCode(String transactionsOutCode) {
		this.transactionsOutCode = transactionsOutCode;
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
}
