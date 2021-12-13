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
@Table(name ="assets")
public class Assets extends BaseEntity{

	@ManyToOne
	@JoinColumn(nullable=false)
	private Items itemsId;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Invoices invoicesId;
	
	@Column(nullable=false)
	private String assetsName;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private StatusesAssets statusesAssetsId;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private StatusesInOut statuses_in_out_id;
	
	@Column
	private LocalDate assetsExpired;

	
	public Items getItemsId() {
		return itemsId;
	}

	public void setItemsId(Items itemsId) {
		this.itemsId = itemsId;
	}

	public Invoices getInvoicesId() {
		return invoicesId;
	}

	public void setInvoicesId(Invoices invoicesId) {
		this.invoicesId = invoicesId;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public StatusesAssets getStatusesAssetsId() {
		return statusesAssetsId;
	}

	public void setStatusesAssetsId(StatusesAssets statusesAssetsId) {
		this.statusesAssetsId = statusesAssetsId;
	}

	public StatusesInOut getStatuses_in_out_id() {
		return statuses_in_out_id;
	}

	public void setStatuses_in_out_id(StatusesInOut statuses_in_out_id) {
		this.statuses_in_out_id = statuses_in_out_id;
	}

	public LocalDate getAssetsExpired() {
		return assetsExpired;
	}

	public void setAssetsExpired(LocalDate assetsExpired) {
		this.assetsExpired = assetsExpired;
	}
	
	
}
