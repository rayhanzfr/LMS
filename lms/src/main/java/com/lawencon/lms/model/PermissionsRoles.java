package com.lawencon.lms.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PermissionsRoles {
	@ManyToOne
	@JoinColumn(name = "permissions_id")
	private Permissions permissionsId;
	
	@ManyToOne
	@JoinColumn(name = "roles_id")
	private Roles rolesId;
	
	public Permissions getPermissionsId() {
		return permissionsId;
	}
	public void setPermissionsId(Permissions permissionsId) {
		this.permissionsId = permissionsId;
	}
	public Roles getRolesId() {
		return rolesId;
	}
	public void setRolesId(Roles rolesId) {
		this.rolesId = rolesId;
	}
	
	
}
