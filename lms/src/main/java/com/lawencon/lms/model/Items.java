package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

public class Items extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "files_id")
	private Files files;
	
	@ManyToOne
	@JoinColumn(name = "item_types_id")
	private ItemsTypes itemsTypes;
	
	@ManyToOne
	@JoinColumn(name = "item_brands_id")
	private ItemsBrands itemsBrands;
	
	@Column(unique = true,length = 15,nullable = false)
	private String itemsCode;
	
	@Column(length = 30)
	private String itemsName;

	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}

	public ItemsTypes getItemsTypes() {
		return itemsTypes;
	}

	public void setItemsTypes(ItemsTypes itemsTypes) {
		this.itemsTypes = itemsTypes;
	}

	public ItemsBrands getItemsBrands() {
		return itemsBrands;
	}

	public void setItemsBrands(ItemsBrands itemsBrands) {
		this.itemsBrands = itemsBrands;
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
