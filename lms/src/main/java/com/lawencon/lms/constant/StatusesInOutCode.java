package com.lawencon.lms.constant;

public enum StatusesInOutCode {
	CHECKIN("CHECKIN"),CHECKOUT("CHECKOUT");

	private String code;

	StatusesInOutCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
