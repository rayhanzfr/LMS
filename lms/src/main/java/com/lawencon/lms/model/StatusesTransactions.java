package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class StatusesTransactions extends BaseEntity {
	@ManyToOne
	@JoinColumn(name="statuses_assets_id")
	private StatusesAssets statusesAssets;
	
	@Column(unique=true,nullable=false,length = 15)
	private String statusesTransactionsCode;
	
	@Column(nullable=false,length = 30)
	private String statusesTransactionsName;

	public StatusesAssets getStatusesAssets() {
		return statusesAssets;
	}

	public void setStatusesAssets(StatusesAssets statusesAssets) {
		this.statusesAssets = statusesAssets;
	}

	public String getStatusesTransactionsCode() {
		return statusesTransactionsCode;
	}

	public void setStatusesTransactionsCode(String statusesTransactionsCode) {
		this.statusesTransactionsCode = statusesTransactionsCode;
	}

	public String getStatusesTransactionsName() {
		return statusesTransactionsName;
	}

	public void setStatusesTransactionsName(String statusesTransactionsName) {
		this.statusesTransactionsName = statusesTransactionsName;
	}
	
	
}
