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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
