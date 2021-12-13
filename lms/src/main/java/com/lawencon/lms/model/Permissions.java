package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class Permissions extends BaseEntity{
	@Column(unique = true,name = "permissions_code",nullable = false, length = 15)
	private String permissionsCode;
	
	@Column(name = "permissions_name", nullable = false, length = 30)
	private String permissionsName;

	public String getPermissionsCode() {
		return permissionsCode;
	}

	public void setPermissionsCode(String permissionsCode) {
		this.permissionsCode = permissionsCode;
	}

	public String getPermissionsName() {
		return permissionsName;
	}

	public void setPermissionsName(String permissionsName) {
		this.permissionsName = permissionsName;
	}
}
