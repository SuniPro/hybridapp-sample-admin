package com.hybirdapp.sample.mngr.sngPrd.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class SngPrdVO extends CmmnMngrVO {

	private String viewType;
	private String sngPrd;
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
	public String getSngPrd() {
		return sngPrd;
	}
	public void setSngPrd(String sngPrd) {
		this.sngPrd = sngPrd;
	}

	
}
