package com.lawencon.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class Files extends BaseEntity{
	
	@Column(nullable = false)
	private byte[] file;
	
	@Column(length = 5, nullable = false)
	private String extensions;

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getExtensions() {
		return extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}
	
	
}
