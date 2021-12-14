package com.lawencon.lms.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table
public class Assets extends BaseEntity{

	@ManyToOne
	@JoinColumn(nullable=false)
	private Items items;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Invoices invoices;
	
	@Column(nullable=false)
	private String assetsName;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private StatusesAssets statusesAssets;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private StatusesInOut statusesInOut;
	
	@Column
	private LocalDate assetsExpired;

	
	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public Invoices getInvoices() {
		return invoices;
	}

	public void setInvoices(Invoices invoices) {
		this.invoices = invoices;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public StatusesAssets getStatusesAssets() {
		return statusesAssets;
	}

	public void setStatusesAssets(StatusesAssets statusesAssets) {
		this.statusesAssets = statusesAssets;
	}

	public StatusesInOut getStatusesInOut() {
		return statusesInOut;
	}

	public void setStatusesInOut(StatusesInOut statusesInOut) {
		this.statusesInOut = statusesInOut;
	}

	public LocalDate getAssetsExpired() {
		return assetsExpired;
	}

	public void setAssetsExpired(LocalDate assetsExpired) {
		this.assetsExpired = assetsExpired;
	}	
}
