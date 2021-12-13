package com.lawencon.lms.dto.assets;

import java.util.List;

public class GetAllAssetsDto {
	private List<AssetsDataDto> data;
	private String msg;

	public List<AssetsDataDto> getData() {
		return data;
	}

	public void setData(List<AssetsDataDto> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
