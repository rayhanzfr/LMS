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
	
	
}
