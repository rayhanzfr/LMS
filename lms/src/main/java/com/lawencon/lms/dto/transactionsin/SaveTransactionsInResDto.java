package com.lawencon.lms.dto.transactionsin;

public class SaveTransactionsInResDto {
	private String code;
	private SaveTransactionsDetailsInResDto listDetail;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public SaveTransactionsDetailsInResDto getListDetail() {
		return listDetail;
	}

	public void setListDetail(SaveTransactionsDetailsInResDto listDetail) {
		this.listDetail = listDetail;
	}

}
