package com.lawencon.lms.constant;

public enum EnumCode {
	ROLES("ROLES"), USERS("USERS"), COMPANIES("COMP"), EMPLOYEES("EMP"), 
	LOCATIONS("LOCTN"), PERMISSIONS("PERMSN"), INVOICES("INVC"), ITEMSTYPE("ITMTYPES"), 
	ITEMS("ITEMS"), ITEMBRANDS("ITMBRANDS"), ASSETS("ASSETS"), TRANSACTIONSIN("LMSIN"), 
	TRANSACTIONSOUT("LMSOUT");

	private String code;

	EnumCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
