package com.lawencon.lms.dto.transactionsin;

import java.util.List;

public class SaveTransactionsInResDto {
	private String code;
	private List<SaveTransactionsDetailsInResDto> listDetail;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<SaveTransactionsDetailsInResDto> getListDetail() {
		return listDetail;
	}

	public void setListDetail(List<SaveTransactionsDetailsInResDto> listDetail) {
		this.listDetail = listDetail;
	}

}
