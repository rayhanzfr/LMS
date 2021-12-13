package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

public class Items extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "files_id")
	private Files filesId;
	
	@ManyToOne
	@JoinColumn(name = "item_types_id")
	private ItemsTypes itemTypes;
	
	@Column(unique = true,length = 15,nullable = false)
	private String itemsCode;
	
	@Column(length = 30)
	private String itemsName;

	public Files getFilesId() {
		return filesId;
	}

	public void setFilesId(Files filesId) {
		this.filesId = filesId;
	}

	public ItemsTypes getItemTypes() {
		return itemTypes;
	}

	public void setItemTypes(ItemsTypes itemTypes) {
		this.itemTypes = itemTypes;
	}

	public String getItemsCode() {
		return itemsCode;
	}

	public void setItemsCode(String itemsCode) {
		this.itemsCode = itemsCode;
	}

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	
}
