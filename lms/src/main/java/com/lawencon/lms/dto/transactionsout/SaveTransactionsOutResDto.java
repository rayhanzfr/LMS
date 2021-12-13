package com.lawencon.lms.dto.transactionsout;

public class SaveTransactionsOutResDto {
	private String id;
	private SaveTransactionsDetailsOutResDto listDetail;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SaveTransactionsDetailsOutResDto getListDetail() {
		return listDetail;
	}
	public void setListDetail(SaveTransactionsDetailsOutResDto listDetail) {
		this.listDetail = listDetail;
	}
}
