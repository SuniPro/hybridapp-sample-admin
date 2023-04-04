package com.hybirdapp.sample.mngr.tckPkg.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class TckPkgVO extends CmmnMngrVO {

	/* ================[팝업 호출 필수값 ]================ */
	private String tckPkg; //
	private String viewType;

	public String getTckPkg() {
		return tckPkg;
	}

	public void setTckPkg(String tckInq) {
		this.tckPkg = tckPkg;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	/* ================[팝업 호출 필수값 End]================ */

}
