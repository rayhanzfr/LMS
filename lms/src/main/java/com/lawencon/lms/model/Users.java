package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table
public class Users extends BaseEntity{

	@ManyToOne
	@JoinColumn(name="roles_id",nullable=false)
	private Roles roles;
	
	@Column(length=50, nullable=false, unique=true)
	private String usersEmail;

	@Column(nullable=false)
	private String usersPassword;

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public String getUsersEmail() {
		return usersEmail;
	}

	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}

	public String getUsersPassword() {
		return usersPassword;
	}

	public void setUsersPassword(String usersPassword) {
		this.usersPassword = usersPassword;
	}
	
	
}
