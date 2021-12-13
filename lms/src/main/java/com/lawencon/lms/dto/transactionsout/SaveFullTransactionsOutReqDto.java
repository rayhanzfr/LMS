package com.lawencon.lms.dto.transactionsout;

import java.util.List;

public class SaveFullTransactionsOutReqDto {
	private SaveTransactionsOutReqDto saveTransactionsOutReqDto;
	private List<SaveTransactionsDetailsOutReqDto> listSaveTransactionsDetailsOutReqDto;
	public SaveTransactionsOutReqDto getSaveTransactionsOutReqDto() {
		return saveTransactionsOutReqDto;
	}
	public void setSaveTransactionsOutReqDto(SaveTransactionsOutReqDto saveTransactionsOutReqDto) {
		this.saveTransactionsOutReqDto = saveTransactionsOutReqDto;
	}
	public List<SaveTransactionsDetailsOutReqDto> getListSaveTransactionsDetailsOutReqDto() {
		return listSaveTransactionsDetailsOutReqDto;
	}
	public void setListSaveTransactionsDetailsOutReqDto(
			List<SaveTransactionsDetailsOutReqDto> listSaveTransactionsDetailsOutReqDto) {
		this.listSaveTransactionsDetailsOutReqDto = listSaveTransactionsDetailsOutReqDto;
	}
}
