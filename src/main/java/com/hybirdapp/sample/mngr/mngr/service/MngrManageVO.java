package com.hybirdapp.sample.mngr.mngr.service;

import com.hybirdapp.sample.cmmn.CmmnMngrVO;

public class MngrManageVO extends CmmnMngrVO {

	private String mngrId;				// 관리자 아이디
	private String mngrPwd;				// 관리자 패스워드
	private String mngrNm;				// 관리자명
	private String mngrEmail;			// 이메일
	private String gradNm;				// 등급이름
	private String mngrJob;				// 부서
	private String mngrGrad;			// 등급
	private String useAt;				// 사용여부
	private String memo;				// 메모
	private String handphone1;			// 핸드폰1
	private String handphone2;			// 핸드폰2
	private String handphone3;			// 핸드폰3
	private String isrtNm;				//등록자명
	private String updtNm;				//수정자 명
	private String sttus;				// 수정 등록 체크
	
	private String searchType;			// 구분
	private String searchText;			// 텍스트
	private String searchFromDt;		// 가입일자 from
	private String searchToDt;			// 가입일자 to	
	
	public String getMngrId() {
		return mngrId;
	}
	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}
	public String getMngrPwd() {
		return mngrPwd;
	}
	public void setMngrPwd(String mngrPwd) {
		this.mngrPwd = mngrPwd;
	}
	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	public String getMngrEmail() {
		return mngrEmail;
	}
	public void setMngrEmail(String mngrEmail) {
		this.mngrEmail = mngrEmail;
	}
	public String getMngrGrad() {
		return mngrGrad;
	}
	public void setMngrGrad(String mngrGrad) {
		this.mngrGrad = mngrGrad;
	}
	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	public String getGradNm() {
		return gradNm;
	}
	public void setGradNm(String gradNm) {
		this.gradNm = gradNm;
	}
	public String getMngrJob() {
		return mngrJob;
	}
	public void setMngrJob(String mngrJob) {
		this.mngrJob = mngrJob;
	}
	public String getHandphone1() {
		return handphone1;
	}
	public void setHandphone1(String handphone1) {
		this.handphone1 = handphone1;
	}
	public String getHandphone2() {
		return handphone2;
	}
	public void setHandphone2(String handphone2) {
		this.handphone2 = handphone2;
	}
	public String getHandphone3() {
		return handphone3;
	}
	public void setHandphone3(String handphone3) {
		this.handphone3 = handphone3;
	}
	public String getSttus() {
		return sttus;
	}
	public void setSttus(String sttus) {
		this.sttus = sttus;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getSearchFromDt() {
		return searchFromDt;
	}
	public void setSearchFromDt(String searchFromDt) {
		this.searchFromDt = searchFromDt;
	}
	public String getSearchToDt() {
		return searchToDt;
	}
	public void setSearchToDt(String searchToDt) {
		this.searchToDt = searchToDt;
	}
	public String getIsrtNm() {
		return isrtNm;
	}
	public void setIsrtNm(String isrtNm) {
		this.isrtNm = isrtNm;
	}
	public String getUpdtNm() {
		return updtNm;
	}
	public void setUpdtNm(String updtNm) {
		this.updtNm = updtNm;
	}
		
	
	
}