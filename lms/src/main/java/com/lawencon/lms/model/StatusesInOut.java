package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table
public class StatusesInOut extends BaseEntity{
	
	@Column(length=15,nullable=false)
	private String statusesInOutCode;
	
	@Column(length=50,nullable=false)
	private String statusesInOutName;

	public String getStatusesInOutCode() {
		return statusesInOutCode;
	}

	public void setStatusesInOutCode(String statusesInOutCode) {
		this.statusesInOutCode = statusesInOutCode;
	}

	public String getStatusesInOutName() {
		return statusesInOutName;
	}

	public void setStatusesInOutName(String statusesInOutName) {
		this.statusesInOutName = statusesInOutName;
	}
	
	
}
