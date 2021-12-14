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
	private String transactionsInCode;

	@Column(nullable = false)
	private LocalDateTime transactionsInDate;

	@ManyToOne()
	@JoinColumn(name = "transactions_out_id", nullable = false)
	private TransactionsOut transactionsOut;

	public String getTransactionsInCode() {
		return transactionsInCode;
	}

	public void setTransactionsInCode(String transactionsInCode) {
		this.transactionsInCode = transactionsInCode;
	}

	public LocalDateTime getTransactionsInDate() {
		return transactionsInDate;
	}

	public void setTransactionsInDate(LocalDateTime transactionsInDate) {
		this.transactionsInDate = transactionsInDate;
	}

	public TransactionsOut getTransactionsOut() {
		return transactionsOut;
	}

	public void setTransactionsOut(TransactionsOut transactionsOut) {
		this.transactionsOut = transactionsOut;
	}

}
