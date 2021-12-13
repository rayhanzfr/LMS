package com.lawencon.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

public class TransactionsIn extends BaseEntity {

	@Column(length = 15, nullable = false)
	private String transactionsCode;
	
	@Column(nullable = false)
	private LocalDateTime transactionsDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private TransactionsOut transactionsOutId;

	public String getTransactionsCode() {
		return transactionsCode;
	}

	public void setTransactionsCode(String transactionsCode) {
		this.transactionsCode = transactionsCode;
	}

	public LocalDateTime getTransactionsDate() {
		return transactionsDate;
	}

	public void setTransactionsDate(LocalDateTime transactionsDate) {
		this.transactionsDate = transactionsDate;
	}

	public TransactionsOut getTransactionsOutId() {
		return transactionsOutId;
	}

	public void setTransactionsOutId(TransactionsOut transactionsOutId) {
		this.transactionsOutId = transactionsOutId;
	}

}
