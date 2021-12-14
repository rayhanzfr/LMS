package com.lawencon.lms.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class Invoices extends BaseEntity {

	@Column(length = 10, nullable = false, unique = true )
	private String invoicesCode;

	@Column(nullable = false)
	private LocalDateTime invoicesDate;

	@Column(length = 50, nullable = false)
	private String storeName;

	@Column(nullable = false)
	private BigDecimal price;

	public String getInvoicesCode() {
		return invoicesCode;
	}

	public void setInvoicesCode(String invoicesCode) {
		this.invoicesCode = invoicesCode;
	}

	public LocalDateTime getInvoicesDate() {
		return invoicesDate;
	}

	public void setInvoicesDate(LocalDateTime invoicesDate) {
		this.invoicesDate = invoicesDate;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}



}
