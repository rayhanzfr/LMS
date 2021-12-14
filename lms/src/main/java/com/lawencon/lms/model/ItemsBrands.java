package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table
public class ItemsBrands extends BaseEntity{
	
	@Column(nullable=false, unique=true, length=15)
	private String itemsBrandsCode;
	
	@Column(length = 50, nullable=false, unique=true)
	private String itemsBrandsName;

	public String getItemsBrandsCode() {
		return itemsBrandsCode;
	}

	public void setItemsBrandsCode(String itemsBrandsCode) {
		this.itemsBrandsCode = itemsBrandsCode;
	}

	public String getItemsBrandsName() {
		return itemsBrandsName;
	}

	public void setItemsBrandsName(String itemsBrandsName) {
		this.itemsBrandsName = itemsBrandsName;
	}
}
