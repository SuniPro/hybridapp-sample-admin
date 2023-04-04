package com.hybirdapp.sample.mngr.tckSng.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class TckSngVO extends CmmnMngrVO {

	private String viewType;
	private String tckSng;
	private String prePageParam;
	
	public String getPrePageParam() {
		return prePageParam;
	}
	public void setPrePageParam(String prePageParam) {
		this.prePageParam = prePageParam;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getTckSng() {
		return tckSng;
	}
	public void setTckSng(String tckSng) {
		this.tckSng = tckSng;
	}

	
}
