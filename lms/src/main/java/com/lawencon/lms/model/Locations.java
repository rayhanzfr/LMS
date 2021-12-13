package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "locations")
public class Locations extends BaseEntity{
	
	@Column(name = "locations_code", length = 10, nullable = false, unique = true)
	private String locationsCode;
	
	@Column(name = "locations_deploy", nullable = false)
	private String locationsDeploy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companies_id", nullable = false)
	private Companies companiesId;

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

	public Companies getCompaniesId() {
		return companiesId;
	}

	public void setCompaniesId(Companies companiesId) {
		this.companiesId = companiesId;
	}

}
