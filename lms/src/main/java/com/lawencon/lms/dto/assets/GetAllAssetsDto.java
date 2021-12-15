package com.lawencon.lms.dto.assets;

import java.util.List;

public class GetAllAssetsDto {
	private List<AssetsDataDto> data;

	public List<AssetsDataDto> getData() {
		return data;
	}

	public void setData(List<AssetsDataDto> data) {
		this.data = data;
	}

}
