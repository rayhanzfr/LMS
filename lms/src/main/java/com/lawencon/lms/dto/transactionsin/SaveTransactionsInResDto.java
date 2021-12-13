package com.lawencon.lms.dto.transactionsin;

public class SaveTransactionsInResDto {
	private String id;
	private SaveTransactionsDetailsInResDto listDetail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SaveTransactionsDetailsInResDto getListDetail() {
		return listDetail;
	}

	public void setListDetail(SaveTransactionsDetailsInResDto listDetail) {
		this.listDetail = listDetail;
	}

}
