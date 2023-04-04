package com.hybirdapp.sample.cmmn.sys.service;

import java.util.List;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class ClCdVO extends CmmnMngrVO {

	private String clGrp;			/*분류그룹*/
	private String clCd;				/*상세코드*/
	private String clNm;			/*코드명*/
	private String clGrpNm;			/*분류 코드명*/
	private String clOrdr;			/*상세코드순서*/
	private String clVal;			/*분류 값*/
	private String clDc;				/*분류 설명*/
	private String useAt;			/*사용여부*/
	private String registId;		/*등록코드*/
	private String registDt;		/*등록일시*/
	private String updtId;			/*수정코드*/
	private String updtDt;			/*수정일시*/
	private String viewType;			/*수정,등록 구분*/
	
	public String getClGrpNm() {
		return clGrpNm;
	}

	public void setClGrpNm(String clGrpNm) {
		this.clGrpNm = clGrpNm;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	private List<ClCdVO> clCdList;

	public String getClGrp() {
		return clGrp;
	}

	public void setClGrp(String clGrp) {
		this.clGrp = clGrp;
	}

	public String getClCd() {
		return clCd;
	}

	public void setClCd(String clCd) {
		this.clCd = clCd;
	}

	public String getClNm() {
		return clNm;
	}

	public void setClNm(String clNm) {
		this.clNm = clNm;
	}

	public String getClOrdr() {
		return clOrdr;
	}

	public void setClOrdr(String clOrdr) {
		this.clOrdr = clOrdr;
	}

	public String getClVal() {
		return clVal;
	}

	public void setClVal(String clVal) {
		this.clVal = clVal;
	}

	public String getClDc() {
		return clDc;
	}

	public void setClDc(String clDc) {
		this.clDc = clDc;
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

	public List<ClCdVO> getClCdList() {
		return clCdList;
	}

	public void setClCdList(List<ClCdVO> clCdList) {
		this.clCdList = clCdList;
	}
}
