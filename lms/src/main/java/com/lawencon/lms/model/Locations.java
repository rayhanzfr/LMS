package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
public class Locations extends BaseEntity{
	
	@Column(length = 10, nullable = false, unique = true)
	private String locationsCode;
	
	@Column(nullable = false)
	private String locationsDeploy;
	
	@ManyToOne()
	@JoinColumn(name = "companies_id", nullable = false)
	private Companies companies;

	public String getLocationsCode() {
		return locationsCode;
	}

	public void setLocationsCode(String locationsCode) {
		this.locationsCode = locationsCode;
	}

	public String getLocationsDeploy() {
		return locationsDeploy;
	}

	public void setLocationsDeploy(String locationsDeploy) {
		this.locationsDeploy = locationsDeploy;
	}

	public Companies getCompanies() {
		return companies;
	}

	public void setCompanies(Companies companies) {
		this.companies = companies;
	}

}
