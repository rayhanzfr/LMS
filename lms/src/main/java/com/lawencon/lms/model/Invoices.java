package com.lawencon.lms.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "invoices")
public class Invoices extends BaseEntity {

	@Column(length = 10, nullable = false, unique = true )
	private String invoicesCode;

	@Column(nullable = false)
	private LocalDateTime invoicesDate;

	@Column(length = 50, nullable = false)
	private String storeName;

	@Column(nullable = false)
	private BigInteger price;

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

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

}
