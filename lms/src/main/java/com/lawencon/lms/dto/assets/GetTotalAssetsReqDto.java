package com.lawencon.lms.dto.assets;

import java.util.List;

import com.lawencon.lms.model.Assets;

public class GetTotalAssetsReqDto {
	private List<Assets> data;
	private Integer total;
	public List<Assets> getData() {
		return data;
	}
	public void setData(List<Assets> data) {
		this.data = data;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
}
