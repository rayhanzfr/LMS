package com.lawencon.lms.constant;

public enum StatusesTransactionsCode {
	READY("READY"), BROKEN("BROKEN"), LOSTORSTOLEN("LOST/STOLEN"), REPAIR("REPAIR");

	private String code;

	StatusesTransactionsCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
