package com.lawencon.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions_detail_in")
public class TransactionDetailIn {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private TransactionsIn transactionsIn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Locations locations_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Employees employeesId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Assets assetsId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private StatusesTransactions statusesTransactionsId;
	
	@Column(nullable = false)
	private LocalDateTime returnDate;

	
	public TransactionsIn getTransactionsIn() {
		return transactionsIn;
	}

	public void setTransactionsIn(TransactionsIn transactionsIn) {
		this.transactionsIn = transactionsIn;
	}

	public Locations getLocations_id() {
		return locations_id;
	}

	public void setLocations_id(Locations locations_id) {
		this.locations_id = locations_id;
	}

	public Employees getEmployeesId() {
		return employeesId;
	}

	public void setEmployeesId(Employees employeesId) {
		this.employeesId = employeesId;
	}

	public Assets getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Assets assetsId) {
		this.assetsId = assetsId;
	}

	public StatusesTransactions getStatusesTransactionsId() {
		return statusesTransactionsId;
	}

	public void setStatusesTransactionsId(StatusesTransactions statusesTransactionsId) {
		this.statusesTransactionsId = statusesTransactionsId;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

}
