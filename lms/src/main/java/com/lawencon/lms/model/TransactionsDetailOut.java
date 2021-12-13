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
@Table(name="transactions_detail_out")
public class TransactionsDetailOut extends BaseEntity{

	@ManyToOne
	@JoinColumn(nullable=false)
	private TransactionsOut transactionsOutId;
	
	@ManyToOne
	@JoinColumn
	private Locations locationsId;
	
	@ManyToOne
	@JoinColumn
	private Employees employeesId;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Assets assetsId;
	
	@Column(nullable=false)
	private LocalDate transactionDetailOutExpired;

	public TransactionsOut getTransactionsOutId() {
		return transactionsOutId;
	}

	public void setTransactionsOutId(TransactionsOut transactionsOutId) {
		this.transactionsOutId = transactionsOutId;
	}

	public Locations getLocationsId() {
		return locationsId;
	}

	public void setLocationsId(Locations locationsId) {
		this.locationsId = locationsId;
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

	public LocalDate getTransactionDetailOutExpired() {
		return transactionDetailOutExpired;
	}

	public void setTransactionDetailOutExpired(LocalDate transactionDetailOutExpired) {
		this.transactionDetailOutExpired = transactionDetailOutExpired;
	}
	
	
}
