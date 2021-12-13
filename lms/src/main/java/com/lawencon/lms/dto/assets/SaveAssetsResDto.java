package com.lawencon.lms.dto.assets;

public class SaveAssetsResDto {
	private SaveAssetsResDataDto data;
	private String message;

	public SaveAssetsResDataDto getData() {
		return data;
	}

	public void setData(SaveAssetsResDataDto data) {
		this.data = data;
	}

	public String getMsg() {
		return message;
	}

	public void setMsg(String message) {
		this.message = message;
	}
}
