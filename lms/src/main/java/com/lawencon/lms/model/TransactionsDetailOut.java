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
	@JoinColumn(name="transactions_out_id",nullable=false)
	private TransactionsOut transactionsOut;
	
	@ManyToOne
	@JoinColumn(name = "locations_id")
	private Locations locations;
	
	@ManyToOne
	@JoinColumn(name = "employees_id")
	private Employees employees;
	
	@ManyToOne
	@JoinColumn(name = "assets_general_id")
	private Assets assetsGeneral;
	
	@ManyToOne
	@JoinColumn(name = "assets_id",nullable=false)
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

	public Assets getAssetsGeneral() {
		return assetsGeneral;
	}

	public void setAssetsGeneral(Assets assetsGeneral) {
		this.assetsGeneral = assetsGeneral;
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
