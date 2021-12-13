package com.lawencon.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class TransactionsDetailIn {

	@ManyToOne()
	@JoinColumn(name = "transactions_in_id", nullable = false)
	private TransactionsIn transactionsIn;

	@ManyToOne()
	@JoinColumn(name = "locations_id")
	private Locations locations;

	@ManyToOne()
	@JoinColumn(name = "employees_id")
	private Employees employees;

	@ManyToOne()
	@JoinColumn(name = "assets_id", nullable = false)
	private Assets assets;

	@ManyToOne()
	@JoinColumn(name = "statuses_transactions_id", nullable = false)
	private StatusesTransactions statusesTransactions;

	@Column(nullable = false)
	private LocalDateTime returnDate;

	
	public TransactionsIn getTransactionsIn() {
		return transactionsIn;
	}

	public void setTransactionsIn(TransactionsIn transactionsIn) {
		this.transactionsIn = transactionsIn;
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

	public StatusesTransactions getStatusesTransactions() {
		return statusesTransactions;
	}

	public void setStatusesTransactions(StatusesTransactions statusesTransactions) {
		this.statusesTransactions = statusesTransactions;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

}
