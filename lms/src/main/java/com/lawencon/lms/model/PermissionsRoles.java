package com.lawencon.lms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class PermissionsRoles extends BaseEntity{
	@ManyToOne
	@JoinColumn(name = "permissions_id")
	private Permissions permissions;
	
	@ManyToOne
	@JoinColumn(name = "roles_id")
	private Roles roles;

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
}
