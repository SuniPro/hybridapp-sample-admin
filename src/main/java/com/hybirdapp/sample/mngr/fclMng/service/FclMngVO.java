package com.hybirdapp.sample.mngr.fclMng.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class FclMngVO extends CmmnMngrVO {

	private List<FclMngVO> prdMngList;

	private List<FclMngVO> pkgMngList;

	private String fclMng;
	private String viewType;
	private String sngPed;

	/* 공통 */
	private String isrtId; 		// 등록자
	private String isrtDate; 	// 등록일자
	private String updtId; 		// 수정자
	private String updtDate; 	// 수정일자
	
	/* 시설목록 */
	private String fcltSeq; 	// 시설 ID
	private String fcltNm;		// 시설 명
	private String cdClass;		// CD_CLASS
	private String cdCode;		// CD_CODE
	private String fcltClass; 	// 시설구분
	
	/* 검색 관련 */
	private String searchText;	// 검색 text
	
	
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	public List<FclMngVO> getPrdMngList() {
		return prdMngList;
	}
	public void setPrdMngList(List<FclMngVO> prdMngList) {
		this.prdMngList = prdMngList;
	}
	public List<FclMngVO> getPkgMngList() {
		return pkgMngList;
	}
	public void setPkgMngList(List<FclMngVO> pkgMngList) {
		this.pkgMngList = pkgMngList;
	}
	public String getFclMng() {
		return fclMng;
	}
	public void setFclMng(String fclMng) {
		this.fclMng = fclMng;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getSngPed() {
		return sngPed;
	}
	public void setSngPed(String sngPed) {
		this.sngPed = sngPed;
	}
	public String getIsrtId() {
		return isrtId;
	}
	public void setIsrtId(String isrtId) {
		this.isrtId = isrtId;
	}
	public String getIsrtDate() {
		return isrtDate;
	}
	public void setIsrtDate(String isrtDate) {
		this.isrtDate = isrtDate;
	}
	public String getUpdtId() {
		return updtId;
	}
	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}
	public String getUpdtDate() {
		return updtDate;
	}
	public void setUpdtDate(String updtDate) {
		this.updtDate = updtDate;
	}
	public String getFcltClass() {
		return fcltClass;
	}
	public void setFcltClass(String fcltClass) {
		this.fcltClass = fcltClass;
	}
	public String getFcltSeq() {
		return fcltSeq;
	}
	public void setFcltSeq(String fcltSeq) {
		this.fcltSeq = fcltSeq;
	}
	public String getFcltNm() {
		return fcltNm;
	}
	public void setFcltNm(String fcltNm) {
		this.fcltNm = fcltNm;
	}
	public String getCdClass() {
		return cdClass;
	}
	public void setCdClass(String cdClass) {
		this.cdClass = cdClass;
	}
	public String getCdCode() {
		return cdCode;
	}
	public void setCdCode(String cdCode) {
		this.cdCode = cdCode;
	}

	
}
