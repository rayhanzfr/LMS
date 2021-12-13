package com.lawencon.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class TransactionsIn extends BaseEntity {

	@Column(length = 15, nullable = false)
	private String transactionsCode;
	
	@Column(nullable = false)
	private LocalDateTime transactionsDate;
	
	@ManyToOne()
	@JoinColumn(name = "transactions_out_id", nullable = false)
	private TransactionsOut transactionsOut;

	
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

	public TransactionsOut getTransactionsOut() {
		return transactionsOut;
	}

	public void setTransactionsOut(TransactionsOut transactionsOut) {
		this.transactionsOut = transactionsOut;
	}



}
