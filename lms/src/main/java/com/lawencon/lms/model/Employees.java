package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table
public class Employees extends BaseEntity{

	@OneToOne
	@JoinColumn(nullable=false)
	private Users users;
	
	@Column(length=255, nullable=false)
	private String employeesFullname;
	
	@Column(length=255)
	private String employeesAddress;

	@Column(length=14, nullable=false,unique=true)
	private String employeesPhoneNumber;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getEmployeesFullname() {
		return employeesFullname;
	}

	public void setEmployeesFullname(String employeesFullname) {
		this.employeesFullname = employeesFullname;
	}

	public String getEmployeesAddress() {
		return employeesAddress;
	}

	public void setEmployeesAddress(String employeesAddress) {
		this.employeesAddress = employeesAddress;
	}

	public String getEmployeesPhoneNumber() {
		return employeesPhoneNumber;
	}

	public void setEmployeesPhoneNumber(String employeesPhoneNumber) {
		this.employeesPhoneNumber = employeesPhoneNumber;
	}
}
