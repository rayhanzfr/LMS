package com.lawencon.lms.constant;

public enum StatusesAssetsCode {
	DEPLOYABLE("DEP"), UNDEPLOYABLE("UNDEP"), ARCHIVED("ARCHV"), PENDING("PEND");

	private String code;

	StatusesAssetsCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
