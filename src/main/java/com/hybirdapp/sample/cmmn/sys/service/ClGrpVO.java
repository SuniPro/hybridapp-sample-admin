package com.hybirdapp.sample.cmmn.sys.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class ClGrpVO extends CmmnMngrVO {

	private String clGrp;				/*공콩코드*/
	private String clGrpNm;			/*분류 그룹 명*/
	private String clGrpDc;			/*분류 그룹 설명*/
	private String useAt;				/*사용여부*/
	private String registId;			/*등록코드*/
	private String registDt;			/*등록일시*/
	private String updtId;				/*수정코드*/
	private String updtDt;				/*수정일시*/
	private String viewType;				/*수정,등록 구분*/
	
	private String searchTyCd;			// 검색 구분 값
	private String searchText;			// 검색 text
	
	
	public String getSearchTyCd() {
		return searchTyCd;
	}

	public void setSearchTyCd(String searchTyCd) {
		this.searchTyCd = searchTyCd;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	private List<ClGrpVO> codeMngList;

	public String getClGrp() {
		return clGrp;
	}

	public void setClGrp(String clGrp) {
		this.clGrp = clGrp;
	}

	public String getClGrpNm() {
		return clGrpNm;
	}

	public void setClGrpNm(String clGrpNm) {
		this.clGrpNm = clGrpNm;
	}

	public String getClGrpDc() {
		return clGrpDc;
	}

	public void setClGrpDc(String clGrpDc) {
		this.clGrpDc = clGrpDc;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
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

	public List<ClGrpVO> getCodeMngList() {
		return codeMngList;
	}

	public void setCodeMngList(List<ClGrpVO> codeMngList) {
		this.codeMngList = codeMngList;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	
	
	
}
