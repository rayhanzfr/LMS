package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="employees")
public class Employees extends BaseEntity{

	@OneToOne
	@JoinColumn(nullable=false)
	private Users usersId;
	
	@Column(length=255, nullable=false)
	private String employeesFullname;
	
	@Column(length=255)
	private String employeesAddress;

	@Column(length=14, nullable=false,unique=true)
	private String employeesPhoneNumber;

	public Users getUsersId() {
		return usersId;
	}

	public void setUsersId(Users usersId) {
		this.usersId = usersId;
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
