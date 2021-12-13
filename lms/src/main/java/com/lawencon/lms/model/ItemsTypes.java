package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="items_types")
public class ItemsTypes extends BaseEntity{
	
	@Column(nullable=false, unique=true, length=15)
	private String itemsTypesCode;
	
	@Column(length = 50, nullable=false, unique=true)
	private String itemsTypesName;

	public String getItemsTypesCode() {
		return itemsTypesCode;
	}

	public void setItemsTypesCode(String itemsTypesCode) {
		this.itemsTypesCode = itemsTypesCode;
	}

	public String getItemsTypesName() {
		return itemsTypesName;
	}

	public void setItemsTypesName(String itemsTypesName) {
		this.itemsTypesName = itemsTypesName;
	}
		
}
