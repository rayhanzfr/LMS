package com.lawencon.lms.dto.transactionsout;

import java.util.List;

public class SaveTransactionsOutResDto {
	private String id;
	private List<SaveTransactionsDetailsOutResDto> listDetail;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<SaveTransactionsDetailsOutResDto> getListDetail() {
		return listDetail;
	}
	public void setListDetail(List<SaveTransactionsDetailsOutResDto> listDetail) {
		this.listDetail = listDetail;
	}
	
}
