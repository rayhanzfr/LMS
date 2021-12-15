package com.lawencon.lms.constant;

public enum StatusesInOutCode {
	DEPLOYABLE("DEP"), UNDEPLOYABLE("UNDEP"), ARCHIVED("ARCHV"), PENDING("PEND");

	private String code;

	StatusesInOutCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
