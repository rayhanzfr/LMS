package com.lawencon.lms.constant;

public enum StatusesInOutCode {
	CHECKIN("CIN"),CHECKOUT("COUT");

	private String code;

	StatusesInOutCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
