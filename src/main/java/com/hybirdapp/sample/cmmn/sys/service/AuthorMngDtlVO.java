package com.hybirdapp.sample.cmmn.sys.service;

import com.hybirdapp.sample.cmmn.CmmnVO;

public class AuthorMngDtlVO extends CmmnVO {

	private String authorId;
	private String authorLangCd;
	private String authorNm;
	private String authorDc;
	private String registId;
	private String registDt;
	private String updtId;
	private String updtDt;
	private String isDelete;
	
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAuthorLangCd() {
		return authorLangCd;
	}
	public void setAuthorLangCd(String authorLangCd) {
		this.authorLangCd = authorLangCd;
	}
	public String getAuthorNm() {
		return authorNm;
	}
	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}
	public String getAuthorDc() {
		return authorDc;
	}
	public void setAuthorDc(String authorDc) {
		this.authorDc = authorDc;
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
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
}
