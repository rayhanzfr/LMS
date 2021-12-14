package com.lawencon.lms.dto.assets;

public class UpdateAssetsResDto {
	private UpdateAssetsDataDto data;
	private String message;

	public UpdateAssetsDataDto getData() {
		return data;
	}

	public void setData(UpdateAssetsDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
