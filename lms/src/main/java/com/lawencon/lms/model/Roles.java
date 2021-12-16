package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import com.lawencon.base.BaseEntity;

@Entity
public class Roles extends BaseEntity {
	@Column(unique = true,nullable = false, length = 15)
	private String rolesCode;
	
	@Column(nullable = false, length = 30)
	private String rolesName;

	public String getRolesCode() {
		return rolesCode;
	}

	public void setRolesCode(String rolesCode) {
		this.rolesCode = rolesCode;
	}

	public String getRolesName() {
		return rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}
	  
	  
}