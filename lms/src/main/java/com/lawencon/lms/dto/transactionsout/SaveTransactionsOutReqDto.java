package com.lawencon.lms.dto.transactionsout;

public class SaveTransactionsOutReqDto {
	private String transactionsOutCode;
	private String checkOutDate;
	
	public String getTransactionsOutCode() {
		return transactionsOutCode;
	}
	public void setTransactionsOutCode(String transactionsOutCode) {
		this.transactionsOutCode = transactionsOutCode;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	
}
