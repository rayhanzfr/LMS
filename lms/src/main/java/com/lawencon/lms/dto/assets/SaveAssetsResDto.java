package com.lawencon.lms.dto.assets;

public class SaveAssetsResDto {
	private SaveAssetsDataDto data;
	private String message;

	public SaveAssetsDataDto getData() {
		return data;
	}

	public void setData(SaveAssetsDataDto data) {
		this.data = data;
	}

	public String getMsg() {
		return message;
	}

	public void setMsg(String message) {
		this.message = message;
	}
}
