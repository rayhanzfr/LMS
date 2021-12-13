package com.lawencon.lms.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table
public class TransactionsDetailOut extends BaseEntity{

	@ManyToOne
	@JoinColumn(nullable=false)
	private TransactionsOut transactionsOut;
	
	@ManyToOne
	@JoinColumn
	private Locations locations;
	
	@ManyToOne
	@JoinColumn
	private Employees employees;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Assets assets;
	
	@Column(nullable=false)
	private LocalDate transactionDetailOutExpired;

	public TransactionsOut getTransactionsOut() {
		return transactionsOut;
	}

	public void setTransactionsOut(TransactionsOut transactionsOut) {
		this.transactionsOut = transactionsOut;
	}

	public Locations getLocations() {
		return locations;
	}

	public void setLocations(Locations locations) {
		this.locations = locations;
	}

	public Employees getEmployees() {
		return employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public LocalDate getTransactionDetailOutExpired() {
		return transactionDetailOutExpired;
	}

	public void setTransactionDetailOutExpired(LocalDate transactionDetailOutExpired) {
		this.transactionDetailOutExpired = transactionDetailOutExpired;
	}
}
