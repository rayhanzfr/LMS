package com.lawencon.lms.dto.assets;

public class GetByIdAssetsDto {
	private AssetsDataDto data;
	private String msg;

	public AssetsDataDto getData() {
		return data;
	}

	public void setData(AssetsDataDto data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
