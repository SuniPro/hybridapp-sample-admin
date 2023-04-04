package com.hybirdapp.sample.cmmn.sys.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.CmmnVO;

public class AuthorMngMenuVO extends CmmnVO {

	private String authCd;		/*권한코드*/
	private String menuCd;		/*메뉴코드*/
	private String registId;	/*등록코드*/
	private String registDt;	/*등록일시*/
	private String updtId;		/*수정코드*/
	private String updtDt;		/*수정일시*/
	
	private List<AuthorMngMenuVO> authorMngMenuVOList;

	public String getAuthCd() {
		return authCd;
	}

	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}

	public String getMenuCd() {
		return menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}

	public String getRegistId() {
		return registId;
	}

	public void setRegistId(String registId) {
		this.registId = registId;
	}

	public String getRegistDt() {
		return registDt;
	}

	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}

	public String getUpdtId() {
		return updtId;
	}

	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}

	public String getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

	public List<AuthorMngMenuVO> getAuthorMngMenuVOList() {
		return authorMngMenuVOList;
	}

	public void setAuthorMngMenuVOList(List<AuthorMngMenuVO> authorMngMenuVOList) {
		this.authorMngMenuVOList = authorMngMenuVOList;
	}


}
